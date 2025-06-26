import mysql.connector
from faker import Faker
import random
from tqdm import tqdm
import time

faker = Faker("pt_BR")

conn = mysql.connector.connect(
    host='localhost',
    user='root',
    password='rafael',
    database='TupiGames'
)
cursor = conn.cursor()

def email_existe(tabela: str, email: str) -> bool:
    cursor.execute(f"SELECT 1 FROM {tabela} WHERE email = %s LIMIT 1", (email,))
    return cursor.fetchone() is not None

def get_ids(tabela, campo):
    cursor.execute(f"SELECT {campo} FROM {tabela}")
    return [row[0] for row in cursor.fetchall()]

TOTAL_ETAPAS = 9
etapa_atual = 0

def atualizar_status_geral():
    global etapa_atual
    etapa_atual += 1
    progresso = (etapa_atual / TOTAL_ETAPAS) * 100
    print(f"📦 Progresso geral: {etapa_atual}/{TOTAL_ETAPAS} etapas concluídas ({progresso:.2f}%)\n")

def inserir_escolas(qtd=20):
    escolas = []
    emails_gerados = set()
    for i in tqdm(range(qtd), desc="Inserindo escolas"):
        nome_empresa = faker.company().replace(" ", "").replace(",", "").replace(".", "").lower()
        while True:
            email = f"{nome_empresa}.{random.randint(1, 99999999)}@example.com"
            if email not in emails_gerados and not email_existe('escola', email):
                emails_gerados.add(email)
                break
        escolas.append((email, faker.company(), 'senha123'))
        if len(escolas) >= 100:
            cursor.executemany("INSERT INTO escola (email, nome_escola, senha) VALUES (%s, %s, %s)", escolas)
            conn.commit()
            escolas = []
    if escolas:
        cursor.executemany("INSERT INTO escola (email, nome_escola, senha) VALUES (%s, %s, %s)", escolas)
        conn.commit()
    atualizar_status_geral()

def inserir_turmas(qtd_por_escola=5):
    escola_ids = get_ids('escola', 'escola_id')
    turmas = []
    for escola_id in tqdm(escola_ids, desc="Inserindo turmas por escola"):
        for _ in range(qtd_por_escola):
            periodo = random.choice(['Matutino', 'Vespertino', 'Noturno'])
            turmas.append((
                random.randint(100000000, 999999999), 
                faker.word() + " " + faker.word(), 
                periodo, 
                random.randint(20, 40), 
                escola_id
            ))
            if len(turmas) >= 100:
                cursor.executemany("""
                    INSERT INTO turma (turma_id, nome_turma, periodo, qnt_alunos, escola_id) 
                    VALUES (%s, %s, %s, %s, %s)
                """, turmas)
                conn.commit()
                turmas = []
    if turmas:
        cursor.executemany("""
            INSERT INTO turma (turma_id, nome_turma, periodo, qnt_alunos, escola_id) 
            VALUES (%s, %s, %s, %s, %s)
        """, turmas)
        conn.commit()
    atualizar_status_geral()

def inserir_alunos(qtd=500):
    escola_ids = get_ids('escola', 'escola_id')
    alunos = []
    for i in tqdm(range(qtd), desc="Inserindo alunos"):
        escola_id = random.choice(escola_ids)
        alunos.append((
            faker.first_name() + " " + faker.last_name(), 
            'senha123', 
            escola_id
        ))
        if len(alunos) >= 100:
            cursor.executemany("""
                INSERT INTO aluno (nome_aluno, senha, escola_id)
                VALUES (%s, %s, %s)
            """, alunos)
            conn.commit()
            alunos = []
    if alunos:
        cursor.executemany("""
            INSERT INTO aluno (nome_aluno, senha, escola_id)
            VALUES (%s, %s, %s)
        """, alunos)
        conn.commit()
    atualizar_status_geral()

def inserir_professores(qtd=30):
    escola_ids = get_ids('escola', 'escola_id')
    professores = []
    emails_gerados = set()
    for i in tqdm(range(qtd), desc="Inserindo professores"):
        while True:
            email = f"{faker.first_name().lower()}.{faker.last_name().lower()}.{random.randint(1, 99999999)}@example.com"
            if email not in emails_gerados and not email_existe('professor', email):
                emails_gerados.add(email)
                break
        escola_id = random.choice(escola_ids)
        professores.append((
            faker.date_of_birth(minimum_age=25, maximum_age=65).strftime('%d/%m/%Y'),
            email,
            faker.first_name() + " " + faker.last_name(),
            'senha123',
            int(time.time() * 1000),
            escola_id
        ))
        if len(professores) >= 50:
            cursor.executemany("""
                INSERT INTO professor (data_nascimento, email, nome_professor, senha, ultima_vez_ativo, escola_id)
                VALUES (%s, %s, %s, %s, %s, %s)
            """, professores)
            conn.commit()
            professores = []
    if professores:
        cursor.executemany("""
            INSERT INTO professor (data_nascimento, email, nome_professor, senha, ultima_vez_ativo, escola_id)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, professores)
        conn.commit()
    atualizar_status_geral()

def vincular_alunos_turmas():
    turma_ids = get_ids('turma', 'turma_id')
    aluno_ids = get_ids('aluno', 'aluno_id')
    links = []
    
    for aluno_id in tqdm(aluno_ids, desc="Vinculando alunos às turmas"):
        num_turmas = random.randint(1, 3)
        turmas_aluno = random.sample(turma_ids, min(num_turmas, len(turma_ids)))
        for turma_id in turmas_aluno:
            links.append((turma_id, aluno_id))
        
        if len(links) >= 100:
            cursor.executemany("INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (%s, %s)", links)
            conn.commit()
            links = []
    
    if links:
        cursor.executemany("INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (%s, %s)", links)
        conn.commit()
    atualizar_status_geral()

def vincular_professores_turmas():
    turma_ids = get_ids('turma', 'turma_id')
    professor_ids = get_ids('professor', 'professor_id')
    links = []
    
    for professor_id in tqdm(professor_ids, desc="Vinculando professores às turmas"):
        num_turmas = random.randint(2, 5)
        turmas_professor = random.sample(turma_ids, min(num_turmas, len(turma_ids)))
        for turma_id in turmas_professor:
            links.append((turma_id, professor_id))
        
        if len(links) >= 50:
            cursor.executemany("INSERT INTO turma_professor (turma_id, professor_id) VALUES (%s, %s)", links)
            conn.commit()
            links = []
    
    if links:
        cursor.executemany("INSERT INTO turma_professor (turma_id, professor_id) VALUES (%s, %s)", links)
        conn.commit()
    atualizar_status_geral()

def inserir_atividades(qtd=100):
    turma_ids = get_ids('turma', 'turma_id')
    professor_ids = get_ids('professor', 'professor_id')
    escola_ids = get_ids('escola', 'escola_id')
    atividades = []
    atividade_turmas = []
    
    cursor.execute("SELECT professor_id, email FROM professor")
    professores_emails = {row[0]: row[1] for row in cursor.fetchall()}
    
    cursor.execute("SELECT escola_id, email FROM escola")
    escolas_emails = {row[0]: row[1] for row in cursor.fetchall()}
    
    for _ in tqdm(range(qtd), desc="Inserindo atividades"):
        if random.random() < 0.8:
            criador_id = random.choice(professor_ids)
            criador = professores_emails.get(criador_id, f"professor_{criador_id}@example.com")
        else:
            criador_id = random.choice(escola_ids)
            criador = escolas_emails.get(criador_id, f"escola_{criador_id}@example.com")
            
        atividade_code = random.randint(10000000, 99999999)
        cursor.execute("""
            INSERT INTO atividade (atividade_code, criador, data_criacao, global, nome_atividade)
            VALUES (%s, %s, %s, %s, %s)
        """, (
            atividade_code,
            criador,
            int(time.time() * 1000),
            False,
            faker.sentence(nb_words=3)
        ))
        atividade_id = cursor.lastrowid
        
        num_turmas = random.randint(1, 3)
        turmas_atividade = random.sample(turma_ids, min(num_turmas, len(turma_ids)))
        for turma_id in turmas_atividade:
            atividade_turmas.append((atividade_id, turma_id))
        
        if len(atividade_turmas) >= 50:
            cursor.executemany("""
                INSERT INTO atividade_turma (atividade_id, turma_id) 
                VALUES (%s, %s)
            """, atividade_turmas)
            conn.commit()
            atividade_turmas = []
    
    if atividade_turmas:
        cursor.executemany("""
            INSERT INTO atividade_turma (atividade_id, turma_id) 
            VALUES (%s, %s)
        """, atividade_turmas)
        conn.commit()
    atualizar_status_geral()


def inserir_questoes_alternativas(qtd_per_atividade=5):
    atividade_ids = get_ids('atividade', 'atividade_id')
    alternativas = []
    
    for atividade_id in tqdm(atividade_ids, desc="Inserindo questões por atividade"):
        for _ in range(qtd_per_atividade):
            cursor.execute("""
                INSERT INTO pergunta (enunciado, imagem, imagem_enunciado, valor, atividade_id)
                VALUES (%s, %s, %s, %s, %s)
            """, (True, False, False, faker.sentence(nb_words=10), atividade_id))
            pergunta_id = cursor.lastrowid
            
            correta = random.randint(0, 3)
            for i in range(4):
                alternativas.append((
                    i == correta,
                    True,
                    False,
                    faker.sentence(nb_words=5),
                    pergunta_id
                ))
            
            if len(alternativas) >= 20:
                cursor.executemany("""
                    INSERT INTO alternativa (acerto, enunciado, imagem, valor, pergunta_id)
                    VALUES (%s, %s, %s, %s, %s)
                """, alternativas)
                conn.commit()
                alternativas = []
    
    if alternativas:
        cursor.executemany("""
            INSERT INTO alternativa (acerto, enunciado, imagem, valor, pergunta_id)
            VALUES (%s, %s, %s, %s, %s)
        """, alternativas)
        conn.commit()
    atualizar_status_geral()

def inserir_respostas():
    aluno_ids = get_ids('aluno', 'aluno_id')
    atividade_ids = get_ids('atividade', 'atividade_id')
    respostas = []
    
    for aluno_id in tqdm(aluno_ids, desc="Inserindo respostas dos alunos"):
        num_atividades = max(1, int(len(atividade_ids) * random.uniform(0.2, 0.5)))
        atividades_aluno = random.sample(atividade_ids, num_atividades)
        
        for atividade_id in atividades_aluno:
            cursor.execute("SELECT id FROM pergunta WHERE atividade_id = %s", (atividade_id,))
            pergunta_ids = [row[0] for row in cursor.fetchall()]
            
            acertos = 0
            for pergunta_id in pergunta_ids:
                cursor.execute("SELECT id, acerto FROM alternativa WHERE pergunta_id = %s", (pergunta_id,))
                alternativas = cursor.fetchall()
                
                alt_id, acerto = random.choice(alternativas)
                if acerto:
                    acertos += 1
            
            respostas.append((
                random.randint(1, 999999999999),
                acertos,
                int(time.time() * 1000),
                acertos * 100,  
                len(pergunta_ids),
                aluno_id,
                atividade_id
            ))
            
            if len(respostas) >= 50:
                cursor.executemany("""
                    INSERT INTO resposta (resposta_id, acertos, enviado, pontos, total, aluno_id, atividade_id)
                    VALUES (%s, %s, %s, %s, %s, %s, %s)
                """, respostas)
                conn.commit()
                respostas = []
    
    if respostas:
        cursor.executemany("""
            INSERT INTO resposta (resposta_id, acertos, enviado, pontos, total, aluno_id, atividade_id)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
        """, respostas)
        conn.commit()
    atualizar_status_geral()

try:
    inserir_escolas(40)
    inserir_turmas(10)
    inserir_alunos(1000)
    inserir_professores(60)
    vincular_alunos_turmas()
    vincular_professores_turmas()
    inserir_atividades(200)
    inserir_questoes_alternativas(5)
    inserir_respostas()

    print("✅ População do banco de dados concluída com sucesso!")
except Exception as e:
    print(f"❌ Erro durante a população do banco: {e}")
finally:
    cursor.close()
    conn.close()