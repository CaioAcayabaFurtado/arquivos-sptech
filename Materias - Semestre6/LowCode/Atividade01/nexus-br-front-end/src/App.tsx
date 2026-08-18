import { useEffect, useState, type ReactNode } from 'react'

/* ---------- Ícones (SVG inline, stroke currentColor) ---------- */
type IconProps = { className?: string }

const Icon = ({ children, className }: { children: ReactNode; className?: string }) => (
  <svg
    className={className}
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth={1.6}
    strokeLinecap="round"
    strokeLinejoin="round"
    aria-hidden="true"
  >
    {children}
  </svg>
)

const ShieldIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <path d="M12 3 5 6v5c0 4.2 2.9 7.6 7 9 4.1-1.4 7-4.8 7-9V6l-7-3Z" />
    <path d="m9 12 2 2 4-4" />
  </Icon>
)
const BotIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <rect x="4" y="7" width="16" height="12" rx="3" />
    <path d="M12 7V4M8 13h.01M16 13h.01M9 17h6" />
  </Icon>
)
const UsersIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <circle cx="9" cy="9" r="3" />
    <path d="M2 20c0-3.3 3.1-5 7-5s7 1.7 7 5" />
    <path d="M16 4.5a3 3 0 0 1 0 5.8M17 15c2.7.4 5 1.9 5 5" />
  </Icon>
)
const GearIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <circle cx="12" cy="12" r="3" />
    <path d="M12 3v2m0 14v2M3 12h2m14 0h2M5.6 5.6l1.4 1.4m10 10 1.4 1.4m0-12.8-1.4 1.4m-10 10-1.4 1.4" />
  </Icon>
)
const VideoIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <rect x="3" y="6" width="12" height="12" rx="2" />
    <path d="m15 10 6-3v10l-6-3" />
  </Icon>
)
const LockIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <rect x="5" y="11" width="14" height="9" rx="2" />
    <path d="M8 11V8a4 4 0 0 1 8 0v3M12 15v2" />
  </Icon>
)
const CheckIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <path d="m5 12 5 5 9-11" />
  </Icon>
)
const ArrowIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <path d="M5 12h14m-6-6 6 6-6 6" />
  </Icon>
)
const PlusIcon = (p: IconProps) => (
  <Icon className={p.className}>
    <path d="M12 5v14M5 12h14" />
  </Icon>
)
const StarIcon = (p: IconProps) => (
  <svg viewBox="0 0 24 24" fill="currentColor" className={p.className} aria-hidden="true">
    <path d="m12 2 2.9 6.3 6.9.7-5.1 4.6 1.4 6.8L12 17.8 5.9 20.4l1.4-6.8L2.2 9l6.9-.7L12 2Z" />
  </svg>
)

/* ---------- Dados ---------- */
const features = [
  {
    icon: ShieldIcon,
    title: 'Proteção para menores',
    desc: 'Verificação de idade, ambientes restritos e filtros automáticos que bloqueiam conteúdo impróprio antes de chegar às crianças.',
  },
  {
    icon: BotIcon,
    title: 'Moderação com IA brasileira',
    desc: 'Nossa IA entende gírias, contexto e o português do dia a dia — detectando bullying e discurso de ódio em tempo real.',
  },
  {
    icon: UsersIcon,
    title: 'Comunidades orgânicas',
    desc: 'Canais de texto, tópicos e cargos personalizados para reunir turmas, times e grupos de interesse sem ruído.',
  },
  {
    icon: GearIcon,
    title: 'Gestão de servidores',
    desc: 'Painel completo com permissões granulares, logs de auditoria e automações para administrar tudo com poucos cliques.',
  },
  {
    icon: VideoIcon,
    title: 'Voz e vídeo nativo',
    desc: 'Chamadas em alta definição com baixa latência, hospedadas no Brasil para conversas fluidas sem travamentos.',
  },
  {
    icon: LockIcon,
    title: 'Controle parental total',
    desc: 'Pais acompanham atividade, definem horários e recebem relatórios semanais — com transparência e privacidade.',
  },
]

const steps = [
  { n: '01', title: 'Crie sua comunidade', desc: 'Configure seu servidor em menos de dois minutos com modelos prontos.' },
  { n: '02', title: 'Defina as regras', desc: 'Escolha níveis de proteção, filtros de idade e permissões por cargo.' },
  { n: '03', title: 'Convide os membros', desc: 'Compartilhe um link seguro com verificação para entrar no grupo.' },
  { n: '04', title: 'Modere e acompanhe', desc: 'A IA cuida do dia a dia enquanto você vê tudo pelo painel.' },
]

const metrics = [
  { value: '+52.000', label: 'usuários ativos' },
  { value: '+8.400', label: 'servidores criados' },
  { value: '4.9★', label: 'avaliação média' },
  { value: '99.8%', label: 'uptime garantido' },
]

const testimonials = [
  {
    quote:
      'Finalmente uma plataforma onde posso reunir meus alunos sem me preocupar. A moderação por IA pega coisas que eu nunca veria a tempo.',
    name: 'Profa. Marina Alves',
    role: 'Coordenadora pedagógica — Colégio Horizonte',
    initials: 'MA',
  },
  {
    quote:
      'Meus filhos usam todos os dias e eu recebo o relatório semanal. Consigo dar liberdade com tranquilidade, coisa que nenhum outro app oferecia.',
    name: 'Rodrigo Ferreira',
    role: 'Pai de dois adolescentes',
    initials: 'RF',
  },
  {
    quote:
      'Migrei minha comunidade de 6 mil pessoas e a diferença é gritante. Voz nativa no Brasil, moderação de verdade e suporte que responde em português.',
    name: 'Bianca Souza',
    role: 'Criadora de conteúdo — @bibigames',
    initials: 'BS',
  },
]

const plans = [
  {
    name: 'Grátis',
    price: 'R$ 0',
    period: '/sempre',
    desc: 'Para começar sua primeira comunidade.',
    features: ['Até 50 membros', 'Canais de texto e voz', 'Moderação básica por IA', 'Suporte pela comunidade'],
    cta: 'Começar grátis',
    highlight: false,
  },
  {
    name: 'Pro',
    price: 'R$ 29',
    period: '/mês',
    desc: 'Para comunidades que querem crescer com segurança.',
    features: [
      'Membros ilimitados',
      'Moderação avançada por IA',
      'Voz e vídeo em HD',
      'Cargos e automações',
      'Suporte prioritário',
    ],
    cta: 'Assinar o Pro',
    highlight: true,
  },
  {
    name: 'Escolar',
    price: 'Sob consulta',
    period: '',
    desc: 'Licença institucional para escolas e ONGs.',
    features: ['Painel de pais e responsáveis', 'Relatórios de atividade', 'Verificação institucional', 'Onboarding dedicado'],
    cta: 'Falar com vendas',
    highlight: false,
  },
]

const faqs = [
  {
    q: 'Como o NexoBR protege menores de idade?',
    a: 'Usamos verificação de idade na entrada, ambientes com conteúdo restrito por faixa etária e filtros automáticos que bloqueiam mensagens, links e mídias impróprias antes que cheguem a usuários mais jovens.',
  },
  {
    q: 'A moderação por IA funciona em português?',
    a: 'Sim. Nossa IA foi treinada com linguagem brasileira, incluindo gírias e contexto regional, para detectar bullying, discurso de ódio e assédio com muito mais precisão do que soluções genéricas.',
  },
  {
    q: 'O NexoBR está de acordo com a LGPD?',
    a: 'Totalmente. Os dados são armazenados no Brasil, com criptografia de ponta a ponta em conversas privadas e controle transparente sobre quais informações são coletadas e por quê.',
  },
  {
    q: 'Qual a diferença para o Discord?',
    a: 'Somos 100% brasileiros: servidores hospedados no país, suporte em português, moderação que entende nossa cultura e recursos de controle parental pensados para famílias e escolas — algo que plataformas internacionais não oferecem.',
  },
  {
    q: 'Como funciona o controle parental?',
    a: 'Pais e responsáveis vinculam a conta do menor e passam a acompanhar atividade, definir horários de uso e receber relatórios semanais, sempre respeitando a privacidade das conversas dentro dos limites de segurança.',
  },
  {
    q: 'Existe suporte para escolas e ONGs?',
    a: 'Sim, com o plano Escolar. Ele inclui verificação institucional, painel para responsáveis, relatórios de atividade e um time de onboarding dedicado para configurar tudo com você.',
  },
]

/* ---------- Componentes ---------- */
function Logo({ className = '' }: { className?: string }) {
  return (
    <span className={`flex items-center gap-2.5 ${className}`}>
      <span className="relative grid h-9 w-9 place-items-center rounded-xl bg-primary text-primary-foreground">
        <svg viewBox="0 0 24 24" className="h-5 w-5" fill="none" stroke="currentColor" strokeWidth={2.4} strokeLinecap="round" strokeLinejoin="round">
          <path d="M5 19V5l14 14V5" />
        </svg>
      </span>
      <span className="font-display text-lg font-bold tracking-tight text-foreground">
        Nexo<span className="text-primary">BR</span>
      </span>
    </span>
  )
}

function ChatMockup() {
  const rows = [
    { u: 'Helena', c: 'bg-accent/80', m: 'Gente, a live de hoje vai ser às 20h! 🎉', t: '19:42' },
    { u: 'Diego', c: 'bg-secondary', m: 'Bora! Já deixei o lembrete ativado aqui', t: '19:43' },
    { u: 'NexoBot', c: 'bg-primary', m: '🛡️ Mensagem suspeita bloqueada automaticamente no canal #geral', t: '19:44', bot: true },
    { u: 'Camila', c: 'bg-accent/80', m: 'Amei a moderação, muito mais tranquilo agora', t: '19:45' },
  ]
  return (
    <div className="relative rounded-2xl border border-border bg-card/80 p-3 shadow-2xl backdrop-blur-sm">
      {/* barra de janela */}
      <div className="flex items-center gap-1.5 px-2 pb-3 pt-1">
        <span className="h-2.5 w-2.5 rounded-full bg-primary/70" />
        <span className="h-2.5 w-2.5 rounded-full bg-accent/70" />
        <span className="h-2.5 w-2.5 rounded-full bg-secondary/70" />
        <span className="ml-3 font-mono text-[11px] text-muted-foreground">servidor · comunidade-brasil</span>
      </div>
      <div className="flex gap-3">
        {/* sidebar de canais */}
        <div className="hidden w-32 shrink-0 flex-col gap-1 rounded-xl bg-muted/60 p-2 sm:flex">
          {['# boas-vindas', '# geral', '# jogos', '🔒 moderação', '🔊 voz'].map((ch, i) => (
            <span
              key={ch}
              className={`rounded-md px-2 py-1.5 font-mono text-[11px] ${
                i === 1 ? 'bg-primary/15 text-primary' : 'text-muted-foreground'
              }`}
            >
              {ch}
            </span>
          ))}
        </div>
        {/* mensagens */}
        <div className="flex flex-1 flex-col gap-3 py-1">
          {rows.map((r) => (
            <div key={r.u + r.t} className="flex gap-2.5">
              <span className={`mt-0.5 grid h-8 w-8 shrink-0 place-items-center rounded-full ${r.c} font-display text-xs font-bold text-primary-foreground`}>
                {r.u.slice(0, 2)}
              </span>
              <div className="min-w-0">
                <p className="flex items-baseline gap-2">
                  <span className={`font-display text-sm font-semibold ${r.bot ? 'text-primary' : 'text-foreground'}`}>{r.u}</span>
                  {r.bot && <span className="rounded bg-primary/15 px-1 font-mono text-[9px] uppercase tracking-wide text-primary">bot</span>}
                  <span className="font-mono text-[10px] text-muted-foreground">{r.t}</span>
                </p>
                <p className="text-[13px] leading-snug text-card-foreground">{r.m}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

function FaqItem({ q, a, open, onToggle }: { q: string; a: string; open: boolean; onToggle: () => void }) {
  return (
    <div className="border-b border-border">
      <button
        onClick={onToggle}
        aria-expanded={open}
        className="flex w-full items-center justify-between gap-4 py-5 text-left transition-colors hover:text-primary"
      >
        <span className="font-display text-base font-semibold sm:text-lg">{q}</span>
        <PlusIcon className={`h-5 w-5 shrink-0 text-primary transition-transform duration-300 ${open ? 'rotate-45' : ''}`} />
      </button>
      <div className={`grid transition-all duration-300 ${open ? 'grid-rows-[1fr] pb-5' : 'grid-rows-[0fr]'}`}>
        <p className="overflow-hidden text-[15px] leading-relaxed text-muted-foreground">{a}</p>
      </div>
    </div>
  )
}

/* ---------- App ---------- */
export default function App() {
  const [scrolled, setScrolled] = useState(false)
  const [menuOpen, setMenuOpen] = useState(false)
  const [openFaq, setOpenFaq] = useState<number | null>(0)

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 20)
    onScroll()
    window.addEventListener('scroll', onScroll, { passive: true })
    return () => window.removeEventListener('scroll', onScroll)
  }, [])

  const nav = [
    { label: 'Recursos', href: '#recursos' },
    { label: 'Como Funciona', href: '#como-funciona' },
    { label: 'Planos', href: '#planos' },
    { label: 'FAQ', href: '#faq' },
  ]

  return (
    <div className="min-h-screen scrollbars-hidden">
      {/* HEADER */}
      <header
        className={`fixed inset-x-0 top-0 z-50 transition-all duration-300 ${
          scrolled ? 'border-b border-border bg-background/80 backdrop-blur-xl' : 'border-b border-transparent'
        }`}
      >
        <div className="mx-auto flex max-w-6xl items-center justify-between px-6 py-4">
          <a href="#top"><Logo /></a>
          <nav className="hidden items-center gap-8 md:flex">
            {nav.map((l) => (
              <a key={l.href} href={l.href} className="text-sm text-card-foreground transition-colors hover:text-primary">
                {l.label}
              </a>
            ))}
          </nav>
          <div className="hidden items-center gap-3 md:flex">
            <a href="#top" className="text-sm text-card-foreground transition-colors hover:text-primary">Entrar</a>
            <a
              href="#planos"
              className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-primary-foreground shadow-lg shadow-primary/20 transition-all hover:brightness-110 hover:shadow-primary/40"
            >
              Começar grátis
            </a>
          </div>
          {/* hambúrguer */}
          <button
            className="md:hidden"
            aria-label="Abrir menu"
            aria-expanded={menuOpen}
            onClick={() => setMenuOpen((o) => !o)}
          >
            <svg viewBox="0 0 24 24" className="h-6 w-6 text-foreground" fill="none" stroke="currentColor" strokeWidth={1.8} strokeLinecap="round">
              {menuOpen ? <path d="M6 6l12 12M18 6 6 18" /> : <path d="M4 7h16M4 12h16M4 17h16" />}
            </svg>
          </button>
        </div>
        {/* drawer mobile */}
        {menuOpen && (
          <div className="border-t border-border bg-background/95 px-6 py-4 backdrop-blur-xl md:hidden">
            <nav className="flex flex-col gap-1">
              {nav.map((l) => (
                <a
                  key={l.href}
                  href={l.href}
                  onClick={() => setMenuOpen(false)}
                  className="rounded-lg px-2 py-3 text-card-foreground transition-colors hover:bg-muted hover:text-primary"
                >
                  {l.label}
                </a>
              ))}
              <a href="#planos" onClick={() => setMenuOpen(false)} className="mt-2 rounded-lg bg-primary px-4 py-3 text-center font-semibold text-primary-foreground">
                Começar grátis
              </a>
            </nav>
          </div>
        )}
      </header>

      {/* HERO */}
      <section id="top" className="relative overflow-hidden pt-32 pb-20 sm:pt-40">
        {/* fundo gradiente */}
        <div className="pointer-events-none absolute inset-0 -z-10">
          <div className="absolute left-1/2 top-0 h-[600px] w-[900px] -translate-x-1/2 rounded-full bg-secondary/25 blur-[140px] animate-pulse-glow" />
          <div className="absolute left-1/2 top-24 h-72 w-72 -translate-x-1/2 rounded-full bg-primary/20 blur-[120px] animate-pulse-glow" />
          <div className="absolute bottom-0 left-10 h-72 w-72 rounded-full bg-accent/15 blur-[120px]" />
          <div
            className="absolute inset-0 opacity-[0.4]"
            style={{
              backgroundImage:
                'linear-gradient(rgba(245,200,0,0.04) 1px, transparent 1px), linear-gradient(90deg, rgba(245,200,0,0.04) 1px, transparent 1px)',
              backgroundSize: '48px 48px',
              maskImage: 'radial-gradient(ellipse 80% 60% at 50% 0%, #000 40%, transparent 100%)',
            }}
          />
        </div>

        <div className="mx-auto grid max-w-6xl items-center gap-14 px-6 lg:grid-cols-[1.05fr_0.95fr]">
          <div className="animate-fade-up">
            <span className="inline-flex items-center gap-2 rounded-full border border-border bg-card/60 px-3 py-1.5 font-mono text-xs text-card-foreground backdrop-blur">
              🇧🇷 Plataforma 100% brasileira
            </span>
            <h1 className="mt-6 font-display text-4xl font-extrabold leading-[1.05] tracking-tight sm:text-6xl">
              Sua comunidade.
              <br />
              Seu controle.
              <br />
              <span className="text-primary">Seu país.</span>
            </h1>
            <p className="mt-6 max-w-lg text-lg leading-relaxed text-muted-foreground">
              O NexoBR reúne pessoas em comunidades digitais com a moderação inteligente e o
              controle parental que famílias e escolas precisam — tudo hospedado no Brasil.
            </p>
            <div className="mt-8 flex flex-col gap-3 sm:flex-row">
              <a
                href="#planos"
                className="group inline-flex items-center justify-center gap-2 rounded-xl bg-primary px-6 py-3.5 font-semibold text-primary-foreground shadow-lg shadow-primary/25 transition-all hover:brightness-110 hover:shadow-primary/40"
              >
                Criar minha comunidade
                <ArrowIcon className="h-4 w-4 transition-transform group-hover:translate-x-1" />
              </a>
              <a
                href="#como-funciona"
                className="inline-flex items-center justify-center gap-2 rounded-xl border border-border bg-card/40 px-6 py-3.5 font-semibold text-foreground backdrop-blur transition-colors hover:border-primary/40 hover:text-primary"
              >
                Ver demonstração
              </a>
            </div>
            <div className="mt-10 flex flex-wrap gap-x-6 gap-y-3 font-mono text-xs text-muted-foreground">
              <span className="inline-flex items-center gap-1.5"><CheckIcon className="h-4 w-4 text-accent" /> Criptografia 256-bit</span>
              <span className="inline-flex items-center gap-1.5"><CheckIcon className="h-4 w-4 text-accent" /> LGPD compliant</span>
              <span className="inline-flex items-center gap-1.5"><CheckIcon className="h-4 w-4 text-accent" /> +50k usuários</span>
            </div>
          </div>
          <div className="animate-fade-up animate-float [animation-delay:0.15s]">
            <ChatMockup />
          </div>
        </div>
      </section>

      {/* DIFERENCIAIS */}
      <section id="recursos" className="mx-auto max-w-6xl px-6 py-24">
        <div className="max-w-2xl">
          <p className="font-mono text-sm uppercase tracking-widest text-primary">Diferenciais</p>
          <h2 className="mt-3 font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Tudo que uma comunidade segura precisa
          </h2>
          <p className="mt-4 text-lg text-muted-foreground">
            Recursos pensados para o Brasil, do primeiro convite ao relatório para os pais.
          </p>
        </div>
        <div className="mt-14 grid gap-5 sm:grid-cols-2 lg:grid-cols-3">
          {features.map((f) => (
            <div
              key={f.title}
              className="group rounded-2xl border border-border bg-card/50 p-6 transition-all duration-300 hover:-translate-y-1 hover:border-primary/40 hover:bg-card"
            >
              <span className="grid h-12 w-12 place-items-center rounded-xl bg-primary/10 text-primary transition-colors group-hover:bg-primary group-hover:text-primary-foreground">
                <f.icon className="h-6 w-6" />
              </span>
              <h3 className="mt-5 font-display text-lg font-semibold">{f.title}</h3>
              <p className="mt-2 text-[15px] leading-relaxed text-muted-foreground">{f.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* COMO FUNCIONA */}
      <section id="como-funciona" className="border-y border-border bg-muted/30">
        <div className="mx-auto max-w-6xl px-6 py-24">
          <div className="max-w-2xl">
            <p className="font-mono text-sm uppercase tracking-widest text-accent">Como funciona</p>
            <h2 className="mt-3 font-display text-3xl font-bold tracking-tight sm:text-4xl">
              Do zero à comunidade em quatro passos
            </h2>
          </div>
          <div className="relative mt-14 grid gap-8 md:grid-cols-4">
            <div className="pointer-events-none absolute left-0 top-7 hidden h-px w-full bg-gradient-to-r from-primary/40 via-accent/40 to-transparent md:block" />
            {steps.map((s) => (
              <div key={s.n} className="relative">
                <span className="relative z-10 grid h-14 w-14 place-items-center rounded-2xl border border-border bg-card font-display text-lg font-bold text-primary">
                  {s.n}
                </span>
                <h3 className="mt-5 font-display text-lg font-semibold">{s.title}</h3>
                <p className="mt-2 text-[15px] leading-relaxed text-muted-foreground">{s.desc}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* PROVA SOCIAL */}
      <section className="mx-auto max-w-6xl px-6 py-24">
        <div className="grid gap-4 rounded-3xl border border-border bg-card/50 p-8 sm:grid-cols-4">
          {metrics.map((m) => (
            <div key={m.label} className="text-center">
              <p className="font-display text-3xl font-extrabold text-primary sm:text-4xl">{m.value}</p>
              <p className="mt-1 font-mono text-xs uppercase tracking-wide text-muted-foreground">{m.label}</p>
            </div>
          ))}
        </div>

        <div className="mt-16 max-w-2xl">
          <p className="font-mono text-sm uppercase tracking-widest text-primary">Prova social</p>
          <h2 className="mt-3 font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Confiado por famílias, escolas e criadores
          </h2>
        </div>
        <div className="mt-10 grid gap-5 lg:grid-cols-3">
          {testimonials.map((t) => (
            <figure key={t.name} className="flex flex-col rounded-2xl border border-border bg-card/50 p-6">
              <div className="flex gap-0.5 text-primary">
                {Array.from({ length: 5 }).map((_, i) => (
                  <StarIcon key={i} className="h-4 w-4" />
                ))}
              </div>
              <blockquote className="mt-4 flex-1 text-[15px] leading-relaxed text-card-foreground">"{t.quote}"</blockquote>
              <figcaption className="mt-6 flex items-center gap-3">
                <span className="grid h-10 w-10 place-items-center rounded-full bg-secondary font-display text-sm font-bold text-secondary-foreground">
                  {t.initials}
                </span>
                <span>
                  <span className="block font-display text-sm font-semibold">{t.name}</span>
                  <span className="block text-xs text-muted-foreground">{t.role}</span>
                </span>
              </figcaption>
            </figure>
          ))}
        </div>
      </section>

      {/* PLANOS */}
      <section id="planos" className="border-y border-border bg-muted/30">
        <div className="mx-auto max-w-6xl px-6 py-24">
          <div className="mx-auto max-w-2xl text-center">
            <p className="font-mono text-sm uppercase tracking-widest text-accent">Planos</p>
            <h2 className="mt-3 font-display text-3xl font-bold tracking-tight sm:text-4xl">
              Escolha como sua comunidade cresce
            </h2>
            <p className="mt-4 text-lg text-muted-foreground">Comece de graça. Sem cartão de crédito.</p>
          </div>
          <div className="mt-14 grid items-start gap-6 lg:grid-cols-3">
            {plans.map((p) => (
              <div
                key={p.name}
                className={`relative flex flex-col rounded-3xl border p-8 transition-all ${
                  p.highlight
                    ? 'border-primary/60 bg-card shadow-2xl shadow-primary/10 lg:-translate-y-4'
                    : 'border-border bg-card/50 hover:border-primary/30'
                }`}
              >
                {p.highlight && (
                  <span className="absolute -top-3 left-8 rounded-full bg-primary px-3 py-1 font-mono text-[11px] font-semibold uppercase tracking-wide text-primary-foreground">
                    Mais popular
                  </span>
                )}
                <h3 className="font-display text-xl font-bold">{p.name}</h3>
                <p className="mt-1 text-sm text-muted-foreground">{p.desc}</p>
                <p className="mt-6 flex items-end gap-1">
                  <span className="font-display text-4xl font-extrabold">{p.price}</span>
                  <span className="pb-1 text-sm text-muted-foreground">{p.period}</span>
                </p>
                <ul className="mt-6 flex-1 space-y-3">
                  {p.features.map((feat) => (
                    <li key={feat} className="flex items-start gap-2.5 text-[15px] text-card-foreground">
                      <CheckIcon className="mt-0.5 h-5 w-5 shrink-0 text-accent" />
                      {feat}
                    </li>
                  ))}
                </ul>
                <a
                  href="#top"
                  className={`mt-8 inline-flex items-center justify-center rounded-xl px-5 py-3 font-semibold transition-all ${
                    p.highlight
                      ? 'bg-primary text-primary-foreground shadow-lg shadow-primary/25 hover:brightness-110'
                      : 'border border-border text-foreground hover:border-primary/40 hover:text-primary'
                  }`}
                >
                  {p.cta}
                </a>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* FAQ */}
      <section id="faq" className="mx-auto max-w-3xl px-6 py-24">
        <div className="text-center">
          <p className="font-mono text-sm uppercase tracking-widest text-primary">FAQ</p>
          <h2 className="mt-3 font-display text-3xl font-bold tracking-tight sm:text-4xl">Perguntas frequentes</h2>
        </div>
        <div className="mt-12">
          {faqs.map((f, i) => (
            <FaqItem key={f.q} q={f.q} a={f.a} open={openFaq === i} onToggle={() => setOpenFaq(openFaq === i ? null : i)} />
          ))}
        </div>
      </section>

      {/* CTA FINAL + FOOTER */}
      <footer className="border-t border-border">
        <div className="mx-auto max-w-6xl px-6 py-20">
          <div className="relative overflow-hidden rounded-3xl border border-primary/30 bg-card p-10 text-center sm:p-16">
            <div className="pointer-events-none absolute inset-0 -z-10">
              <div className="absolute left-1/2 top-0 h-64 w-96 -translate-x-1/2 rounded-full bg-primary/20 blur-[100px]" />
            </div>
            <h2 className="mx-auto max-w-xl font-display text-3xl font-extrabold tracking-tight sm:text-4xl">
              Sua comunidade merece um lugar seguro e brasileiro
            </h2>
            <p className="mx-auto mt-4 max-w-lg text-lg text-muted-foreground">
              Junte-se a mais de 52 mil pessoas que já conversam com tranquilidade no NexoBR.
            </p>
            <a
              href="#top"
              className="mt-8 inline-flex items-center gap-2 rounded-xl bg-primary px-7 py-4 font-semibold text-primary-foreground shadow-lg shadow-primary/25 transition-all hover:brightness-110"
            >
              Abrir minha comunidade agora
              <ArrowIcon className="h-4 w-4" />
            </a>
          </div>

          <div className="mt-16 grid gap-10 sm:grid-cols-2 lg:grid-cols-5">
            <div className="lg:col-span-2">
              <Logo />
              <p className="mt-4 max-w-xs text-sm leading-relaxed text-muted-foreground">
                A plataforma brasileira de comunidades digitais com segurança para todas as idades.
              </p>
            </div>
            {[
              { h: 'Produto', links: ['Recursos', 'Planos', 'Segurança', 'Novidades'] },
              { h: 'Empresa', links: ['Sobre', 'Blog', 'Carreiras', 'Imprensa'] },
              { h: 'Legal', links: ['Privacidade', 'Termos', 'LGPD', 'Cookies'] },
            ].map((col) => (
              <div key={col.h}>
                <h4 className="font-display text-sm font-semibold">{col.h}</h4>
                <ul className="mt-4 space-y-2.5">
                  {col.links.map((l) => (
                    <li key={l}>
                      <a href="#top" className="text-sm text-muted-foreground transition-colors hover:text-primary">{l}</a>
                    </li>
                  ))}
                </ul>
              </div>
            ))}
          </div>

          <div className="mt-14 flex flex-col items-center justify-between gap-4 border-t border-border pt-8 sm:flex-row">
            <p className="font-mono text-xs text-muted-foreground">© 2026 NexoBR · Feito no Brasil 🇧🇷</p>
            <div className="flex items-center gap-3 font-mono text-[11px] text-muted-foreground">
              <span className="inline-flex items-center gap-1.5 rounded-full border border-border px-2.5 py-1"><LockIcon className="h-3.5 w-3.5 text-accent" /> LGPD</span>
              <span className="inline-flex items-center gap-1.5 rounded-full border border-border px-2.5 py-1"><ShieldIcon className="h-3.5 w-3.5 text-accent" /> ISO 27001</span>
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}
