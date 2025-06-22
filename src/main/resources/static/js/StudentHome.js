document.addEventListener('DOMContentLoaded', function() {
    const containerAtividadesAnteriores = document.querySelector('.bg-white .grid-cols-1.sm\\:grid-cols-2.md\\:grid-cols-3');

    console.log("Container de atividades encontrado:", containerAtividadesAnteriores);

    if (containerAtividadesAnteriores) {
        console.log("Dimensões do container:",
            containerAtividadesAnteriores.offsetWidth,
            containerAtividadesAnteriores.offsetHeight);
    }

    const mensagemNenhumaAtividade = Array.from(
        document.querySelectorAll('.bg-white .text-gray-500.jersey-10.text-xl')
    ).find(el => el.textContent.trim() === 'Nenhuma atividade anterior encontrada');

    const containerMensagem = mensagemNenhumaAtividade ? mensagemNenhumaAtividade.closest('.col-span-full, .text-center, div') : null;

    console.log("Mensagem encontrada:", !!mensagemNenhumaAtividade);
    console.log("Container da mensagem:", containerMensagem);

    function getAlunoLogado() {
        const alunoString = sessionStorage.getItem('aluno');
        if (alunoString) {
            try {
                const aluno = JSON.parse(alunoString);
                console.log("Dados do aluno na sessão:", aluno);
                return aluno.nomeAluno || aluno.nome;
            } catch (e) {
                console.error("Erro ao parsear aluno do sessionStorage:", e);
            }
        }

        const usuarioString = sessionStorage.getItem('usuario');
        if (usuarioString) {
            try {
                const usuario = JSON.parse(usuarioString);
                console.log("Dados do usuário na sessão:", usuario);
                return usuario.nome || usuario.nomeAluno || usuario.name || usuario.aluno;
            } catch (e) {
                console.error("Erro ao parsear usuário do sessionStorage:", e);
            }
        }

        console.error("Nenhum dado de aluno encontrado no sessionStorage");
        return null;
    }

    function renderizarAtividadesAnteriores(respostas) {
        if (!containerAtividadesAnteriores) {
            console.error("Container de atividades anteriores não encontrado");
            return;
        }

        if (!respostas || respostas.length === 0) {
            console.log("Nenhuma resposta encontrada, mantendo mensagem padrão");
            return;
        }

        console.log("Respostas encontradas:", respostas.length, "- Ocultando mensagem padrão");
        if (containerMensagem) {
            containerMensagem.style.display = 'none';
        }

        const respostasLimitadas = respostas.slice(0, 3);
        console.log("Mostrando as últimas 3 atividades:", respostasLimitadas);

        containerAtividadesAnteriores.innerHTML = '';

        containerAtividadesAnteriores.setAttribute('style',
            'display: grid !important; ' +
            'grid-template-columns: repeat(3, minmax(0, 1fr)) !important; ' +
            'gap: 1.5rem !important;'
        );

        respostasLimitadas.forEach(resposta => {
            const cardDiv = document.createElement('div');
            cardDiv.className = 'bg-[#7C4A24] p-4 rounded-lg text-[#66AD70] shadow hover:shadow-lg transition-shadow';
            cardDiv.innerHTML = `
                <h3 class="text-xl jersey-10 text-[#66AD70] mb-2">${resposta.atividade ? resposta.atividade.nomeAtividade : 'Sem nome'}</h3>
                <p class="text-xl jersey-10 text-[#F5E5C7] mb-2">Código: ${resposta.atividade ? resposta.atividade.atividadeCode : 'N/A'}</p>
                <p class="text-sm jersey-10 text-[#F5E5C7] mb-1">Pontuação: ${resposta.pontos !== undefined ? resposta.pontos : 'N/A'}</p>
                <p class="text-sm jersey-10 text-[#F5E5C7]">Data: ${formatarData(resposta.enviado)}</p>
            `;
            containerAtividadesAnteriores.appendChild(cardDiv);
        });

        console.log("Número de cards renderizados:", containerAtividadesAnteriores.children.length);
    }

    function formatarData(timestamp) {
        if (!timestamp) return 'N/A';

        try {
            const data = new Date(timestamp);
            return data.toLocaleDateString('pt-BR');
        } catch (e) {
            console.error("Erro ao formatar data:", e);
            return 'N/A';
        }
    }

    async function buscarRespostasDoAluno() {
        try {
            const nomeAluno = getAlunoLogado();
            if (!nomeAluno) {
                throw new Error('Nome do aluno não encontrado na sessão');
            }

            console.log("Nome do aluno obtido:", nomeAluno);

            const response = await fetch(`/api/v1/resposta/getLast3ByAluno?nomeAluno=${encodeURIComponent(nomeAluno)}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                console.error("Resposta não OK:", response.status, response.statusText);
                throw new Error(`Erro ao buscar respostas do aluno: ${response.status}`);
            }

            const respostas = await response.json();
            console.log("Respostas recebidas:", respostas);

            respostas.sort((a, b) => b.resposta_id - a.resposta_id);

            renderizarAtividadesAnteriores(respostas);
        } catch (error) {
            console.error('Erro:', error);
            if (containerAtividadesAnteriores) {
                containerAtividadesAnteriores.innerHTML = `
                    <div class="col-span-full text-center py-8">
                        <p class="text-gray-500 jersey-10 text-xl">
                            Erro ao carregar atividades: ${error.message}
                        </p>
                    </div>
                `;
            }
        }
    }

    window.verDetalhesResposta = function(respostaId) {
        window.location.href = `/aluno/respostas/feedback/${respostaId}`;
    };

    buscarRespostasDoAluno();
});
