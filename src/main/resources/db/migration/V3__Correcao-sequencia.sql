SELECT setval('public.aluno_aluno_id_seq', (SELECT MAX(aluno_id) FROM public.aluno));
SELECT setval('public.professor_professor_id_seq', (SELECT MAX(professor_id) FROM public.professor));
SELECT setval('public.atividade_atividade_id_seq', (SELECT MAX(atividade_id) FROM public.atividade));
SELECT setval('public.pergunta_id_seq', (SELECT MAX(id) FROM public.pergunta));
SELECT setval('public.alternativa_id_seq', (SELECT MAX(id) FROM public.alternativa));
SELECT setval('public.escola_escola_id_seq', (SELECT MAX(escola_id) FROM public.escola));
SELECT setval('public.turma_seq', (SELECT MAX(turma_id) FROM public.turma));
SELECT setval('public.resposta_seq', COALESCE((SELECT MAX(resposta_id) FROM public.resposta), 1));