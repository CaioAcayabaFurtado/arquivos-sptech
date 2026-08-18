# Plano: Landing Page NexoBR

## Contexto

O usuário quer transformar o App.tsx atual (um fundo de bolinhas interativo placeholder) em uma landing page completa e premium para o **NexoBR** — uma plataforma brasileira de comunidades digitais com foco em segurança para menores de idade, posicionada como alternativa nacional ao Discord.

A landing deve ter alta conversão, visual tech/dark premium, e convencer tanto usuários finais quanto famílias e escolas.

---

## Stance e Identidade Visual

**Stance:** Dark tech premium — canvas escuro (deep navy), acentos em ciano elétrico e roxo vibrante, branco/cinza claro para texto. Inspirado em plataformas SaaS premium e produtos de segurança digital. O brief especifica explicitamente a paleta, então honramos isso em vez das sugestões do `create_make_theme`.

**Tipografia:**
- Display: **Outfit** (weight 700–800) — moderna, tech, sem serifa com caráter próprio
- Body: **Inter** (weight 400–500) — legível, familiar, confiável
- Mono: **JetBrains Mono** — para métricas, labels e elementos de interface

**Paleta de tokens — inspirada na bandeira do Brasil, modo dark** (em `src/index.css` via Tailwind v4 `@theme`):

| Token | Valor | Referência |
|---|---|---|
| `--background` | `#05120a` | verde-floresta quase preto |
| `--foreground` | `#eef5ee` | branco levemente esverdeado |
| `--card` | `#0b1f11` | painel escuro verde-musgo |
| `--card-foreground` | `#c8dfc8` | texto suave nos cards |
| `--primary` | `#f5c800` | amarelo ouro da bandeira |
| `--primary-foreground` | `#05120a` | texto escuro sobre amarelo |
| `--secondary` | `#1e4d8c` | azul profundo do globo |
| `--secondary-foreground` | `#ffffff` | texto sobre azul |
| `--muted` | `#0f2a17` | superfície discreta |
| `--muted-foreground` | `#6b9975` | labels e captions |
| `--accent` | `#22c55e` | verde vivo (confiança/segurança) |
| `--accent-foreground` | `#05120a` | texto sobre verde |
| `--border` | `rgba(245,200,0,0.15)` | borda sutil dourada |
| `--ring` | `#f5c800` | focus ring dourado |
| `--radius` | `0.75rem` | |

Gradientes hero: radial de verde-escuro + azul profundo, com brilho dourado central sutil.

---

## Estrutura de Arquivos

- **`src/index.css`** — Adicionar imports Google Fonts (Outfit, Inter, JetBrains Mono) antes do `@import 'tailwindcss'`, depois os tokens via `@theme`.
- **`src/App.tsx`** — Substituir completamente pelo componente de landing page. O dot-grid atual é apenas um placeholder sem funcionalidade relevante.
- **Componentes inline no App.tsx** (sem arquivos separados para manter simplicidade — a landing é uma única página).

---

## Seções da Landing (em ordem)

### 1. Header (fixo, com blur)
- Logo SVG inline (letras "N" estilizadas + "NexoBR")
- Nav links: Recursos, Como Funciona, Planos, FAQ
- Botão CTA: "Começar grátis" (ciano)
- Menu hambúrguer para mobile

### 2. Hero Section
- Badge: "🇧🇷 Plataforma 100% Brasileira"
- Headline grande: "Sua comunidade. Seu controle. Seu país."
- Subtítulo convincente sobre segurança e comunidade
- CTAs: "Criar minha comunidade" (primário) + "Ver demonstração" (secundário/ghost)
- Mockup: UI simulada de chat/servidor em cards estilizados com gradiente
- Mini trust indicators: "256-bit criptografia", "LGPD compliant", "+50k usuários"

### 3. Diferenciais (6 cards em grid)
- Proteção para menores de idade (ícone escudo)
- Moderação com IA brasileira (ícone robô)
- Comunidades orgânicas (ícone grupo)
- Gestão de servidores (ícone engrenagem)
- Voz e vídeo nativo (ícone câmera)
- Controle parental total (ícone cadeado)

### 4. Como Funciona (4 etapas horizontais)
- Criar comunidade → Configurar regras → Convidar membros → Moderar e acompanhar
- Linha de progresso visual conectando as etapas
- Ícone + número + título + descrição curta por etapa

### 5. Prova Social
- Métricas: "+52.000 usuários", "+8.400 servidores", "4.9★ avaliação", "99.8% uptime"
- 3 depoimentos fictícios (professora, pai de família, criador de conteúdo)
- Cards com avatar placeholder + nome + cargo/contexto + citação

### 6. Planos (3 tiers)
- **Grátis**: comunidades básicas, até 50 membros
- **Pro** (destacado como popular): moderação avançada, sem limite de membros, R$ 29/mês
- **Escolar**: licença institucional, painel de pais, relatórios, preço sob consulta

### 7. FAQ (accordion com state React)
- 5–6 perguntas: segurança para menores, moderação, LGPD, diferença do Discord, suporte

### 8. Footer
- Logo + tagline curta
- 4 colunas de links: Produto, Empresa, Legal, Redes Sociais
- CTA final: "Abrir minha comunidade agora"
- Copyright + selos de segurança/compliance

---

## Implementação Técnica

- **React state** para: menu mobile (open/close), FAQ accordion (qual item está aberto), header scroll (adicionar blur quando scrollar)
- **CSS animations sutis**: fade-in nas seções via `@keyframes`, hover em cards (`transform: translateY(-4px)`), brilho nos botões CTA
- **Gradientes**: hero com mesh gradient (ciano + roxo radiais sobrepostos), cards com borda gradient via `border: 1px solid` com `background-clip`
- **Mockup de chat**: div estilizado com balões de mensagem fake, nomes fictícios, timestamps — sem imagem externa
- **Responsividade**: breakpoint em `md:` (768px) — grid de 1 coluna para 2–3 colunas, hero empilhado para lado a lado, nav mobile com drawer

---

## Verificação

1. Preview no painel do Figma Make (hot reload automático)
2. Verificar responsividade redimensionando o preview
3. Testar accordion do FAQ (abrir/fechar)
4. Testar menu mobile
5. Verificar scroll do header (blur appearance)
