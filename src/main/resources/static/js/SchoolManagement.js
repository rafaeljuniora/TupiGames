// Variáveis globais para armazenar os alunos, professores e turmas carregados
let alunosCache = [];
let professoresCache = [];
let turmasCache = [];

function renderAlunos(alunos) {
  const tbody = document.querySelector("#sectionAlunos table tbody");
  if (!tbody) return;

  tbody.innerHTML = "";

  alunos.forEach((aluno) => {
    const tr = document.createElement("tr");
    tr.className = "hover:bg-gray-100";

    const tdNome = document.createElement("td");
    tdNome.className = "py-4 px-6 text-left";
    tdNome.textContent = `${aluno.nomeAluno}`;
    tr.appendChild(tdNome);

    const tdTurma = document.createElement("td");
    tdTurma.className = "py-4 px-6 text-left";
    tdTurma.textContent = aluno.turma || "-";
    tr.appendChild(tdTurma);

    const tdAcoes = document.createElement("td");
    const divAcoes = document.createElement("div");
    divAcoes.className = "action-icons fill-[#475467]";

    divAcoes.innerHTML = `
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 576 512"
            class="hover:fill-gray-400 cursor-pointer view-student-btn"
            data-aluno='${JSON.stringify(aluno)}'
        >
            <path
                d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 448 512"
            class="hover:fill-red-500 cursor-pointer remove-student-btn"
        >
            <path
                d="M135.2 17.7L128 32 32 32C14.3 32 0 46.3 0 64S14.3 96 32 96l384 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-96 0-7.2-14.3C307.4 6.8 296.3 0 284.2 0L163.8 0c-12.1 0-23.2 6.8-28.6 17.7zM416 128L32 128 53.2 467c1.6 25.3 22.6 45 47.9 45l245.8 0c25.3 0 46.3-19.7 47.9-45L416 128z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            class="hover:fill-green-500 cursor-pointer edit-student-btn"
            data-aluno='${JSON.stringify(aluno)}'
        >
            <path
                d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z"
            />
        </svg>
    `;

    tdAcoes.appendChild(divAcoes);
    tr.appendChild(tdAcoes);
    tbody.appendChild(tr);

    adicionarEventosAlunos(divAcoes, aluno);
  });
}

function adicionarEventosAlunos(divAcoes, aluno) {
  const viewBtn = divAcoes.querySelector(".view-student-btn");
  if (viewBtn) {
    viewBtn.addEventListener("click", function () {
      const alunoData = JSON.parse(this.getAttribute("data-aluno"));
      window.openStudentViewModal && window.openStudentViewModal(alunoData);
    });
  }

  const editBtn = divAcoes.querySelector(".edit-student-btn");
  if (editBtn) {
    editBtn.addEventListener("click", function () {
      const alunoData = JSON.parse(this.getAttribute("data-aluno"));
      window.openStudentUpdateModal && window.openStudentUpdateModal(alunoData);
    });
  }

  const removeBtn = divAcoes.querySelector(".remove-student-btn");
  if (removeBtn) {
    removeBtn.addEventListener("click", async function () {

      console.log("==== DEPURAÇÃO REMOÇÃO DE ALUNO ====");
      console.log("Aluno objeto completo:", aluno);


      const alunoId = aluno.id || aluno.aluno_id || (typeof aluno.getId === "function" ? aluno.getId() : null);


      const idParaEnviar = Number(alunoId);

      console.log("ID que será usado para remoção:", idParaEnviar);
      console.log("===================================");

      if (!idParaEnviar) {
        console.error("Não foi possível obter o ID do aluno!");
        Swal.fire({
          title: "Erro",
          text: "Não foi possível identificar o aluno para remoção",
          icon: "error",
        });
        return;
      }

      const result = await Swal.fire({
        title: "Você tem certeza?",
        text: "Essa ação é IRREVERSÍVEL",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim, tenho Certeza!",
        cancelButtonText: "Não, Cancelar!",
      });

      if (result.isConfirmed) {
        try {
          console.log("Enviando requisição para remover aluno com ID:", idParaEnviar);

          await axios.post("/api/v1/aluno/remove", idParaEnviar, {
            headers: {
              "Content-Type": "application/json",
            },
          });

          // Forçar atualização da lista depois de um pequeno atraso
          setTimeout(async () => {
            console.log("Atualizando lista de alunos...");
            await getStudentsBySchoolWithCache();

            // Verificar se o aluno ainda existe na lista atualizada
            const alunoAindaExiste = alunosCache.some(a => {
              const aId = Number(a.id || a.aluno_id);
              const comparaId = Number(idParaEnviar);
              return aId === comparaId;
            });

            if (alunoAindaExiste) {
              console.error("Aluno ainda existe após tentativa de remoção!");
              Swal.fire({
                title: "Erro",
                text: "O aluno parece ter sido removido, mas ainda aparece na lista. Tente recarregar a página.",
                icon: "warning",
              });
            } else {
              await Swal.fire({
                title: "Aluno Removido",
                text: "Aluno foi removido do sistema",
                icon: "success",
              });
            }
          }, 1000);

        } catch (error) {
          console.error("Erro ao remover aluno:", error);
          Swal.fire({
            title: "Erro",
            text: "Ocorreu um erro ao remover o aluno",
            icon: "error",
          });
        }
      }
    });
  }
}

// Função para adicionar eventos aos botões de ação dos professores
function adicionarEventosProfessores(divAcoes, professor) {
  const viewBtn = divAcoes.querySelector(".view-teacher-btn");
  if (viewBtn) {
    viewBtn.addEventListener("click", function () {
      const professorData = JSON.parse(this.getAttribute("data-professor"));
      window.openTeacherViewModal && window.openTeacherViewModal(professorData);
    });
  }

  const editBtn = divAcoes.querySelector(".edit-teacher-btn");
  if (editBtn) {
    editBtn.addEventListener("click", function () {
      const professorData = JSON.parse(this.getAttribute("data-professor"));
      window.openTeacherUpdateModal && window.openTeacherUpdateModal(professorData);
    });
  }

  const removeBtn = divAcoes.querySelector(".remove-teacher-btn");
  if (removeBtn) {
    removeBtn.addEventListener("click", async function () {
      // Log detalhado para depuração
      console.log("==== DEPURAÇÃO REMOÇÃO DE PROFESSOR ====");
      console.log("Professor objeto completo:", professor);

      // Tenta obter o ID do professor de todas as propriedades possíveis
      // A ordem de verificação é importante para priorizar propriedades
      const professorId = professor.id || professor.professor_id || (typeof professor.getId === "function" ? professor.getId() : null);

      // Certifique-se de que o ID seja um número e não um objeto
      const idParaEnviar = Number(professorId);

      console.log("ID que será usado para remoção:", idParaEnviar);
      console.log("===================================");

      if (!idParaEnviar) {
        console.error("Não foi possível obter o ID do professor!");
        Swal.fire({
          title: "Erro",
          text: "Não foi possível identificar o professor para remoção",
          icon: "error",
        });
        return;
      }

      const result = await Swal.fire({
        title: "Você tem certeza?",
        text: "Essa ação é IRREVERSÍVEL",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim, tenho Certeza!",
        cancelButtonText: "Não, Cancelar!",
      });

      if (result.isConfirmed) {
        try {
          console.log("Enviando requisição para remover professor com ID:", idParaEnviar);

          const response = await axios.post("/api/v1/professor/remove", idParaEnviar, {
            headers: {
              "Content-Type": "application/json",
            },
          });

          console.log("Resposta do servidor:", response);

          // Forçar atualização da lista depois de um pequeno atraso
          setTimeout(async () => {
            console.log("Atualizando lista de professores...");
            await getTeachersBySchoolWithCache();

            // Verificar se o professor ainda existe na lista atualizada
            const professorAindaExiste = professoresCache.some(p => {
              const pId = Number(p.id || p.professor_id);
              const comparaId = Number(idParaEnviar);
              return pId === comparaId;
            });

            if (professorAindaExiste) {
              console.error("Professor ainda existe após tentativa de remoção!");
              Swal.fire({
                title: "Erro",
                text: "O professor parece ter sido removido, mas ainda aparece na lista. Tente recarregar a página.",
                icon: "warning",
              });
            } else {
              await Swal.fire({
                title: "Professor Removido",
                text: "Professor foi removido do sistema",
                icon: "success",
              });
            }
          }, 1000);

        } catch (error) {
          console.error("Erro ao remover professor:", error);
          Swal.fire({
            title: "Erro",
            text: "Ocorreu um erro ao remover o professor",
            icon: "error",
          });
        }
      }
    });
  }
}

// Função para renderizar professores na tabela
function renderProfessores(professores) {
  const tbody = document.querySelector("#sectionProfessores table tbody");
  if (!tbody) return; // Sai da função se o tbody não for encontrado

  tbody.innerHTML = "";

  professores.forEach((professor) => {
    const tr = document.createElement("tr");
    tr.className = "hover:bg-gray-100";

    const tdNome = document.createElement("td");
    tdNome.className = "py-4 px-6 text-left";
    tdNome.textContent = `${professor.nomeProfessor}`;
    tr.appendChild(tdNome);

    const tdTurma = document.createElement("td");
    tdTurma.className = "py-4 px-6 text-left";
    tdTurma.textContent = professor.turma || "-";
    tr.appendChild(tdTurma);

    const tdAcoes = document.createElement("td");
    const divAcoes = document.createElement("div");
    divAcoes.className = "action-icons fill-[#475467]";

    divAcoes.innerHTML = `
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 576 512"
            class="hover:fill-gray-400 cursor-pointer view-teacher-btn"
            data-professor='${JSON.stringify(professor)}'
        >
            <path
                d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 448 512"
            class="hover:fill-red-500 cursor-pointer remove-teacher-btn"
        >
            <path
                d="M135.2 17.7L128 32 32 32C14.3 32 0 46.3 0 64S14.3 96 32 96l384 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-96 0-7.2-14.3C307.4 6.8 296.3 0 284.2 0L163.8 0c-12.1 0-23.2 6.8-28.6 17.7zM416 128L32 128 53.2 467c1.6 25.3 22.6 45 47.9 45l245.8 0c25.3 0 46.3-19.7 47.9-45L416 128z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            class="hover:fill-green-500 cursor-pointer edit-teacher-btn"
            data-professor='${JSON.stringify(professor)}'
        >
            <path
                d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z"
            />
        </svg>
    `;

    tdAcoes.appendChild(divAcoes);
    tr.appendChild(tdAcoes);
    tbody.appendChild(tr);

    // Adiciona eventos para os botões
    adicionarEventosProfessores(divAcoes, professor);
  });
}

// Função para renderizar turmas na tabela
function renderTurmas(turmas) {
  const tbody = document.querySelector("#sectionTurmas table tbody");
  if (!tbody) return;

  tbody.innerHTML = "";

  turmas.forEach((turma) => {
    const tr = document.createElement("tr");
    tr.className = "hover:bg-gray-100";

    const tdNome = document.createElement("td");
    tdNome.className = "py-4 px-6 text-left";
    tdNome.textContent = `${turma.nomeTurma}`;
    tr.appendChild(tdNome);

    const tdDescricao = document.createElement("td");
    tdDescricao.className = "py-4 px-6 text-left";
    tdDescricao.textContent = turma.descricao || "-";
    tr.appendChild(tdDescricao);

    const tdAcoes = document.createElement("td");
    const divAcoes = document.createElement("div");
    divAcoes.className = "action-icons fill-[#475467]";

    divAcoes.innerHTML = `
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 576 512"
            class="hover:fill-gray-400 cursor-pointer view-class-btn"
            data-turma='${JSON.stringify(turma)}'
        >
            <path
                d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 448 512"
            class="hover:fill-red-500 cursor-pointer remove-class-btn"
        >
            <path
                d="M135.2 17.7L128 32 32 32C14.3 32 0 46.3 0 64S14.3 96 32 96l384 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-96 0-7.2-14.3C307.4 6.8 296.3 0 284.2 0L163.8 0c-12.1 0-23.2 6.8-28.6 17.7zM416 128L32 128 53.2 467c1.6 25.3 22.6 45 47.9 45l245.8 0c25.3 0 46.3-19.7 47.9-45L416 128z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            class="hover:fill-green-500 cursor-pointer edit-class-btn"
            data-turma='${JSON.stringify(turma)}'
        >
            <path
                d="M495.9 166.6c3.2 8.7 .5 18.4-6.4 24.6l-43.3 39.4c1.1 8.3 1.7 16.8 1.7 25.4s-.6 17.1-1.7 25.4l43.3 39.4c6.9 6.2 9.6 15.9 6.4 24.6c-4.4 11.9-9.7 23.3-15.8 34.3l-4.7 8.1c-6.6 11-14 21.4-22.1 31.2c-5.9 7.2-15.7 9.6-24.5 6.8l-55.7-17.7c-13.4 10.3-28.2 18.9-44 25.4l-12.5 57.1c-2 9.1-9 16.3-18.2 17.8c-13.8 2.3-28 3.5-42.5 3.5s-28.7-1.2-42.5-3.5c-9.2-1.5-16.2-8.7-18.2-17.8l-12.5-57.1c-15.8-6.5-30.6-15.1-44-25.4L83.1 425.9c-8.8 2.8-18.6 .3-24.5-6.8c-8.1-9.8-15.5-20.2-22.1-31.2l-4.7-8.1c-6.1-11-11.4-22.4-15.8-34.3c-3.2-8.7-.5-18.4 6.4-24.6l43.3-39.4C64.6 273.1 64 264.6 64 256s.6-17.1 1.7-25.4L22.4 191.2c-6.9-6.2-9.6-15.9-6.4-24.6c4.4-11.9 9.7-23.3 15.8-34.3l4.7-8.1c6.6-11 14-21.4 22.1-31.2c5.9-7.2 15.7-9.6 24.5-6.8l55.7 17.7c13.4-10.3 28.2-18.9 44-25.4l12.5-57.1c2-9.1 9-16.3 18.2-17.8C227.3 1.2 241.5 0 256 0s28.7 1.2 42.5 3.5c9.2 1.5 16.2 8.7 18.2 17.8l12.5 57.1c15.8 6.5 30.6 15.1 44 25.4l55.7-17.7c8.8-2.8 18.6-.3 24.5 6.8c8.1 9.8 15.5 20.2 22.1 31.2l4.7 8.1c6.1 11 11.4 22.4 15.8 34.3zM256 336a80 80 0 1 0 0-160 80 80 0 1 0 0 160z"
            />
        </svg>
    `;

    tdAcoes.appendChild(divAcoes);
    tr.appendChild(tdAcoes);
    tbody.appendChild(tr);

    adicionarEventosTurmas(divAcoes, turma);
  });
}

// Função para adicionar eventos aos botões de ação das turmas
function adicionarEventosTurmas(divAcoes, turma) {
  const viewBtn = divAcoes.querySelector(".view-class-btn");
  if (viewBtn) {
    viewBtn.addEventListener("click", function () {
      const turmaData = JSON.parse(this.getAttribute("data-turma"));
      window.openClassViewModal && window.openClassViewModal(turmaData);
    });
  }

  const editBtn = divAcoes.querySelector(".edit-class-btn");
  if (editBtn) {
    editBtn.addEventListener("click", function () {
      const turmaData = JSON.parse(this.getAttribute("data-turma"));
      window.openClassUpdateModal && window.openClassUpdateModal(turmaData);
    });
  }

  const removeBtn = divAcoes.querySelector(".remove-class-btn");
  if (removeBtn) {
    removeBtn.addEventListener("click", async function () {
      const turmaId = turma.id;

      const result = await Swal.fire({
        title: "Você tem certeza?",
        text: "Essa ação é IRREVERSÍVEL",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim, tenho Certeza!",
        cancelButtonText: "Não, Cancelar!",
      });

      if (result.isConfirmed) {
        try {
          await axios.post("/api/v1/turma/remove", turmaId, {
            headers: {
              "Content-Type": "application/json",
            },
          });

          await Swal.fire({
            title: "Turma Removida",
            text: "A turma foi removida do sistema",
            icon: "success",
          });
          getClassesBySchoolWithCache();
        } catch (error) {
          console.error("Erro ao remover turma:", error);
          Swal.fire({
            title: "Erro",
            text: "Ocorreu um erro ao remover a turma",
            icon: "error",
          });
        }
      }
    });
  }
}

async function getStudentsBySchoolWithCache(searchQuery = "") {
  try {
    const usuarioSalvo = sessionStorage.getItem("usuario");
    if (!usuarioSalvo) {
      console.error("Nenhum usuário encontrado na sessão");
      return;
    }

    const usuario = JSON.parse(usuarioSalvo);

    const searchData = {
      email: usuario.email,
      searchQuery: searchQuery
    };

    const response = await axios.post(
      "/api/v1/aluno/searchBySchool",
      searchData,
      { headers: { "Content-Type": "application/json" } }
    );

    alunosCache = response.data;
    renderAlunos(alunosCache);

  } catch (error) {
    console.error("Erro ao buscar alunos:", error);

    try {
      const usuarioSalvo = sessionStorage.getItem("usuario");
      const usuario = JSON.parse(usuarioSalvo);

      const response = await axios.post(
        "/api/v1/aluno/getAllBySchool",
        usuario.email,
        { headers: { "Content-Type": "text/plain" } }
      );

      alunosCache = response.data;

      if (searchQuery) {
        const searchLower = searchQuery.toLowerCase();
        alunosCache = alunosCache.filter(aluno => {
          const nome = (aluno.nomeAluno || "").toLowerCase();
          const turma = (aluno.turma || "").toLowerCase();
          return nome.includes(searchLower) || turma.includes(searchLower);
        });
      }

      renderAlunos(alunosCache);
    } catch (fallbackError) {
      console.error("Erro no fallback da busca:", fallbackError);
      Swal.fire({
        title: "Erro",
        text: "Ocorreu um erro ao buscar os alunos",
        icon: "error",
      });
    }
  }
}

async function getTeachersBySchoolWithCache(searchQuery = "") {
  try {
    const usuarioSalvo = sessionStorage.getItem("usuario");
    if (!usuarioSalvo) {
      console.error("Nenhum usuário encontrado na sessão");
      return;
    }

    const usuario = JSON.parse(usuarioSalvo);

    // Usando diretamente o endpoint que sabemos que funciona
    const response = await axios.post(
      "/api/v1/professor/getAllBySchool",
      usuario.email,
      { headers: { "Content-Type": "text/plain" } }
    );

    professoresCache = response.data;

    // Se tem termo de busca, filtra no cliente
    if (searchQuery) {
      const searchLower = searchQuery.toLowerCase();
      professoresCache = professoresCache.filter(professor => {
        const nome = (professor.nomeProfessor || "").toLowerCase();
        const turma = (professor.turma || "").toLowerCase();
        return nome.includes(searchLower) || turma.includes(searchLower);
      });
    }

    renderProfessores(professoresCache);
  } catch (error) {
    console.error("Erro ao buscar professores:", error);
    Swal.fire({
      title: "Erro",
      text: "Não foi possível atualizar a lista de professores. Tente recarregar a página.",
      icon: "error"
    });
  }
}

async function getClassesBySchoolWithCache(searchQuery = "") {
  try {
    const usuarioSalvo = sessionStorage.getItem("usuario");
    if (!usuarioSalvo) {
      console.error("Nenhum usuário encontrado na sessão");
      return;
    }

    const usuario = JSON.parse(usuarioSalvo);

    // Objeto de dados para enviar ao backend
    const searchData = {
      email: usuario.email,
      searchQuery: searchQuery
    };

    // Usando POST com objeto que contém email da escola e query de busca
    const response = await axios.post(
      "/api/v1/turma/searchBySchool",
      searchData,
      { headers: { "Content-Type": "application/json" } }
    );

    turmasCache = response.data;
    renderTurmas(turmasCache);

  } catch (error) {
    console.error("Erro ao buscar turmas:", error);
    // Se a API de busca não existir ainda, faz um fallback para o método original
    // e filtra no cliente
    try {
      const usuarioSalvo = sessionStorage.getItem("usuario");
      const usuario = JSON.parse(usuarioSalvo);

      const response = await axios.post(
        "/api/v1/turma/getAllBySchool",
        usuario.email,
        { headers: { "Content-Type": "text/plain" } }
      );

      turmasCache = response.data;

      // Se tem termo de busca, filtra no cliente
      if (searchQuery) {
        const searchLower = searchQuery.toLowerCase();
        turmasCache = turmasCache.filter(turma => {
          const nome = (turma.nomeTurma || "").toLowerCase();
          const id = (turma.id || "").toString().toLowerCase();
          return nome.includes(searchLower) || id.includes(searchLower);
        });
      }

      renderTurmas(turmasCache);
    } catch (fallbackError) {
      console.error("Erro no fallback da busca de turmas:", fallbackError);
      Swal.fire({
        title: "Erro",
        text: "Ocorreu um erro ao buscar as turmas",
        icon: "error",
      });
    }
  }
}

function configurarBarraPesquisa() {
  const searchInput = document.getElementById("searchStudents");
  const searchButton = searchInput?.parentElement?.nextElementSibling;

  if (searchInput) {
    const novoSearchInput = searchInput.cloneNode(true);
    searchInput.parentNode.replaceChild(novoSearchInput, searchInput);

    let debounceTimer;

    novoSearchInput.addEventListener("input", function () {
      clearTimeout(debounceTimer);
      debounceTimer = setTimeout(() => {
        const value = this.value.trim();
        getStudentsBySchoolWithCache(value);
      }, 300);
    });

    if (searchButton) {
      searchButton.addEventListener("click", function() {
        const value = novoSearchInput.value.trim();
        getStudentsBySchoolWithCache(value);
      });
    }
  }
}

// Configurar a barra de pesquisa de professores
function configurarBarraPesquisaProfessores() {
  const searchInput = document.getElementById("searchTeachers");
  const searchButton = searchInput?.parentElement?.nextElementSibling;

  if (searchInput) {
    // Remove eventos anteriores para evitar duplicações
    const novoSearchInput = searchInput.cloneNode(true);
    searchInput.parentNode.replaceChild(novoSearchInput, searchInput);

    // Timer de debounce para evitar muitas requisições
    let debounceTimer;

    // Adiciona o evento de input com debounce
    novoSearchInput.addEventListener("input", function () {
      clearTimeout(debounceTimer);
      debounceTimer = setTimeout(() => {
        const value = this.value.trim();
        getTeachersBySchoolWithCache(value);
      }, 300); // 300ms de delay
    });

    // Adiciona evento ao botão de pesquisa, se existir
    if (searchButton) {
      searchButton.addEventListener("click", function() {
        const value = novoSearchInput.value.trim();
        getTeachersBySchoolWithCache(value);
      });
    }
  }
}

// Configurar a barra de pesquisa de turmas
function configurarBarraPesquisaTurmas() {
  const searchInput = document.getElementById("searchClasses");
  const searchButton = searchInput?.parentElement?.nextElementSibling;

  if (searchInput) {
    // Remove eventos anteriores para evitar duplicações
    const novoSearchInput = searchInput.cloneNode(true);
    searchInput.parentNode.replaceChild(novoSearchInput, searchInput);

    // Timer de debounce para evitar muitas requisições
    let debounceTimer;

    // Adiciona o evento de input com debounce
    novoSearchInput.addEventListener("input", function () {
      clearTimeout(debounceTimer);
      debounceTimer = setTimeout(() => {
        const value = this.value.trim();
        getClassesBySchoolWithCache(value);
      }, 300); // 300ms de delay
    });

    // Adiciona evento ao botão de pesquisa, se existir
    if (searchButton) {
      searchButton.addEventListener("click", function() {
        const value = novoSearchInput.value.trim();
        getClassesBySchoolWithCache(value);
      });
    }
  }
}

document.addEventListener("DOMContentLoaded", function() {
  const searchInput = document.getElementById("searchStudents");
  if (searchInput) {
    configurarBarraPesquisa();
    console.log("Barra de pesquisa de alunos inicializada");

    getStudentsBySchoolWithCache();
  }

  const searchTeacherInput = document.getElementById("searchTeachers");
  if (searchTeacherInput) {
    configurarBarraPesquisaProfessores();
    console.log("Barra de pesquisa de professores inicializada");

    getTeachersBySchoolWithCache();
  }

  const searchClassInput = document.getElementById("searchClasses");
  if (searchClassInput) {
    configurarBarraPesquisaTurmas();
    console.log("Barra de pesquisa de turmas inicializada");

    getClassesBySchoolWithCache();
  }
});

// Exporta fun��ões para uso global
window.getStudentsBySchoolWithCache = getStudentsBySchoolWithCache;
window.getTeachersBySchoolWithCache = getTeachersBySchoolWithCache;
window.getClassesBySchoolWithCache = getClassesBySchoolWithCache;
