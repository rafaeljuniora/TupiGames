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
            <div class="bg-[#7C4A24] rounded-lg p-4 text-[#66AD70] shadow-md">
                           <div class="flex justify-between items-center mb-2">
                               <span class="text-lg font-bold jersey-10">${atividade.nomeAtividade}</span>
                               <span class="text-sm jersey-10">${atividade.dataAtribuicao || ''}</span>
                           </div>
                           <div class="text-sm mb-2 jersey-10">Código: ${atividade.atividadeCode || ''}</div>
                           <div class="flex justify-between items-center">
                               <div class="text-sm jersey-10">
                                   ${atividade.professor ? `<span>Prof: ${atividade.professor}</span>` : ''}
                               </div>
                               <div class="text-sm">
                                   <span class="bg-[#66AD70] text-[#000000] px-2 py-1 rounded-full text-xs jersey-10">
                                       ${atividade.quantidadeQuestoes || 0} questões
                                   </span>
                               </div>
                           </div>
                       </div>
                   `).join('');
    }

    async function buscarAtividades() {
        try {
            const professorEmail = getProfessorLogado();
            if (!professorEmail) {
                throw new Error('Professor não está logado');
            }

            console.log("Email do professor logado:", professorEmail);

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

            const atividadesDoProfessor = atividades.filter(atividade => {
                            return atividade.professor === professorEmail;
                        });

                        console.log("Atividades da escola:", atividadesDoProfessor);

            atividadesDoProfessor.sort((a, b) => b.id - a.id);

            renderizarAtividades(atividadesDoProfessor);
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
