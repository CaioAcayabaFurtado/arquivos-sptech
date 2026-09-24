import { z } from 'zod';

export const livroSchema = z.object({
  titulo: z.string().trim().min(1, 'titulo é obrigatório'),
  autor: z.string().trim().min(1, 'autor é obrigatório'),
  ano: z.number().int('ano deve ser um número inteiro').optional(),
  genero: z.string().trim().min(1, 'genero não pode ser vazio').optional()
});

export const idSchema = z.coerce.number().int('id deve ser um número inteiro').positive('id deve ser positivo');
