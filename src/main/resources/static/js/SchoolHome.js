document.addEventListener('DOMContentLoaded', function() {
    const containerAtividades = document.querySelector('#atividades-container');

    function getEscolaLogada() {
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
                        Nenhuma atividade recente
                    </p>
                </div>
            `;
            return;
        }

        const atividadesLimitadas = atividades.slice(0, 3);

        containerAtividades.innerHTML = atividadesLimitadas.map(atividade => `

            <div class="bg-[#7C4A24] rounded-lg p-6 text-[#66AD70] shadow-md w-full min-h-[150px]">
                <div class="flex justify-between items-center mb-3">
                    <span class="text-xl jersey-10">${atividade.nomeAtividade}</span>
                    <span class="text-base jersey-10">${atividade.dataAtribuicao || ''}</span>
                </div>
                <div class="text-base mb-3 jersey-10">Código: ${atividade.atividadeCode || ''}</div>
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

    function formatarData(dataString) {
        if (!dataString) return '';

        const data = new Date(dataString);
        return data.toLocaleDateString('pt-BR');
    }

    async function buscarAtividades() {
        try {
            const escolaEmail = getEscolaLogada();
            if (!escolaEmail) {
                throw new Error('Escola não está logada');
            }



            const response = await fetch('/api/v1/atividade/getAllActivities', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            });

            if (!response.ok) {
                throw new Error('Erro ao buscar atividades');
            }

            const todasAtividades = await response.json();


            const atividadesDaEscola = todasAtividades.filter(atividade => {
                return atividade.professor === escolaEmail;
            });


            atividadesDaEscola.sort((a, b) => b.id - a.id);

            renderizarAtividades(atividadesDaEscola);
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

    function carregarTurmas() {
        const usuarioString = sessionStorage.getItem("usuario");
        if (!usuarioString) {
            console.error("Usuário não encontrado no sessionStorage");
            return;
        }

        try {
            const usuario = JSON.parse(usuarioString);
            const email = usuario.email;

            fetch("/api/v1/turma/getAllBySchool", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: email,
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Erro na requisição");
                    }
                    return response.json();
                })
                .then((turmas) => {
                    const turmasContainer = document.querySelector(
                        ".grid.grid-cols-1.md\\:grid-cols-2.lg\\:grid-cols-3.gap-4"
                    );

                    turmasContainer.innerHTML = "";

                    if (!turmas || turmas.length === 0) {
                        turmasContainer.innerHTML = `
                            <div class="col-span-full text-center py-8">
                                <p class="text-gray-500 jersey-10 text-xl">
                                    Nenhuma turma cadastrada
                                </p>
                            </div>`;
                        return;
                    }

                    turmas.forEach((turma) => {
                        const turmaElement = document.createElement("div");
                        turmaElement.className =
                            "bg-[#7C4A24] rounded-lg p-4 text-[#66AD70]";
                        turmaElement.innerHTML = `
                            <div class="flex justify-between items-center mb-2">
                                <h4 class="text-xl jersey-10">${turma.nomeTurma}</h4>
                                <a
                                    href="/turmas/${turma.turma_id}"
                                    class="bg-[#66AD70] rounded-full w-8 h-8 flex items-center justify-center hover:opacity-80 transition-opacity"
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        stroke="#000000"
                                        class="w-5 h-5"
                                    >
                                        <path
                                            stroke-linecap="round"
                                            stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                                        />
                                        <path
                                            stroke-linecap="round"
                                            stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                                        />
                                    </svg>
                                </a>
                            </div>
                            <p class="text-lg jersey-10">
                                Qnt de Alunos: ${turma.quantidadeAlunosAtual || 0}/${turma.qntAlunos || 0}
                            </p>
                            <p class="text-lg jersey-10">
                                Periodo: ${turma.periodo || ""}
                            </p>
                            <div class="mt-2">
                                ${
                                    turma.professores && turma.professores.length > 0
                                        ? Array.from(turma.professores)
                                            .map(
                                                (nomeProfessor) =>
                                                    `<p class="text-lg jersey-10">${nomeProfessor}</p>`
                                            )
                                            .join("")
                                        : "<p class='text-lg jersey-10'>Nenhum professor atribuído</p>"
                                }
                            </div>
                        `;
                        turmasContainer.appendChild(turmaElement);
                    });
                })
                .catch((error) => {
                    console.error("Erro ao carregar turmas:", error);
                });
        } catch (e) {
            console.error("Erro ao parsear usuário do sessionStorage:", e);
        }
    }

    buscarAtividades();
    carregarTurmas();
});

