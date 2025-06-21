document.addEventListener('DOMContentLoaded', function() {
    const containerAtividades = document.querySelector('#atividades-container');

    function getProfessorLogado() {
        const usuarioString = sessionStorage.getItem('usuario');
        if (!usuarioString) {
            console.error("Usuário não encontrado no sessionStorage");
            return null;
        }

        try {
            const usuario = JSON.parse(usuarioString);
            return usuario.email;
        } catch (e) {
            console.error("Erro ao parsear usuário do sessionStorage:", e);
            return null;
        }
    }

    function renderizarAtividades(atividades) {
        if (!atividades || atividades.length === 0) {
            containerAtividades.innerHTML = `
                <div class="col-span-full text-center py-8">
                    <p class="text-gray-500 jersey-10 text-xl">
                        Nenhuma atividade anterior encontrada
                    </p>
                </div>
            `;
            return;
        }

        const atividadesLimitadas = atividades.slice(0, 3);

        containerAtividades.innerHTML = atividadesLimitadas.map(atividade => `
            <div class="bg-[#7C4A24] p-4 rounded-lg text-[#66AD70] shadow hover:shadow-lg transition-shadow">
                <h3 class="text-xl jersey-10 text-[#66AD70] mb-2">${atividade.nomeAtividade}</h3>
                <p class="text-xl jersey-10 text-[#F5E5C7] mb-2">Código: ${atividade.atividadeCode}</p>
            </div>
        `).join('');
    }

    async function buscarAtividades() {
        try {
            const professorEmail = getProfessorLogado();
            if (!professorEmail) {
                throw new Error('Professor não está logado');
            }

            const response = await fetch('/api/v1/atividade/getAllActivities', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Erro ao buscar atividades');
            }

            const atividades = await response.json();
            console.log("Atividades recebidas:", atividades);

            // Ordenar as atividades pelo ID em ordem decrescente (do maior para o menor)
            atividades.sort((a, b) => b.id - a.id);

            renderizarAtividades(atividades);
        } catch (error) {
            console.error('Erro:', error);
            containerAtividades.innerHTML = `
                <div class="col-span-full text-center py-8">
                    <p class="text-gray-500 jersey-10 text-xl">
                        Erro ao carregar atividades
                    </p>
                </div>
            `;
        }
    }
    buscarAtividades();
});
