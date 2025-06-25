document.addEventListener('DOMContentLoaded', function() {
    const containerAtividades = document.querySelector('#atividades-container');

    window.copiarCodigo = function(codigo) {
        navigator.clipboard.writeText(codigo)
            .then(() => {
                const notificacao = document.createElement('div');
                notificacao.textContent = 'Código copiado!';
                notificacao.className = 'fixed top-4 right-4 bg-[#66AD70] text-white px-4 py-2 rounded-md shadow-lg z-50 jersey-10';
                document.body.appendChild(notificacao);

                setTimeout(() => {
                    notificacao.classList.add('opacity-0', 'transition-opacity');
                    setTimeout(() => document.body.removeChild(notificacao), 500);
                }, 2000);
            })
            .catch(err => {
                console.error('Erro ao copiar texto: ', err);
            });
    };


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
                               <span class="text-xl jersey-10">${atividade.nomeAtividade}</span>
                               <span class="text-base jersey-10">${atividade.dataAtribuicao || ''}</span>
                           </div>
                           <div class="text-base mb-2 jersey-10 flex items-center">
                               <span>Código: ${atividade.atividadeCode || ''}</span>
                               <button class="copy-button ml-2 bg-[#66AD70] rounded-full w-6 h-6 flex items-center justify-center hover:opacity-80 transition-opacity"
                                       onclick="copiarCodigo('${atividade.atividadeCode || ''}')">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="#000000" viewBox="0 0 16 16">
                                       <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
                                       <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
                                   </svg>
                               </button>
                           </div>
                           <div class="flex justify-between items-center">
                               <div class="text-base jersey-10">
                                   ${atividade.professor ? `<span>Prof: ${atividade.professor}</span>` : ''}
                               </div>
                               <div class="text-base">
                                   <span class="bg-[#66AD70] text-[#000000] px-2 py-1 rounded-full text-sm jersey-10">
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
