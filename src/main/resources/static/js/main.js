/* ── Portfolio Main JS ──────────────────────────────────────────────────── */

const API = ""; // same origin — Spring Boot serves static files

/* ── Custom Cursor ────────────────────────────────────────────────────────── */
(function initCursor() {
  const cursor = document.getElementById("cursor");
  const dot = document.getElementById("cursorDot");
  let mx = 0,
    my = 0,
    cx = 0,
    cy = 0;

  document.addEventListener("mousemove", (e) => {
    mx = e.clientX;
    my = e.clientY;
    dot.style.left = mx + "px";
    dot.style.top = my + "px";
  });

  function animateCursor() {
    cx += (mx - cx) * 0.12;
    cy += (my - cy) * 0.12;
    cursor.style.left = cx + "px";
    cursor.style.top = cy + "px";
    requestAnimationFrame(animateCursor);
  }
  animateCursor();

  document
    .querySelectorAll("a, button, .project-card, .skill-card")
    .forEach((el) => {
      el.addEventListener("mouseenter", () => cursor.classList.add("hover"));
      el.addEventListener("mouseleave", () => cursor.classList.remove("hover"));
    });
})();

/* ── Navigation ───────────────────────────────────────────────────────────── */
(function initNav() {
  const nav = document.getElementById("nav");
  window.addEventListener("scroll", () => {
    nav.classList.toggle("scrolled", window.scrollY > 50);
  });

  const toggle = document.getElementById("navToggle");
  const menu = document.getElementById("mobileMenu");
  const close = document.getElementById("mobileClose");

  toggle.addEventListener("click", () => menu.classList.add("open"));
  close.addEventListener("click", () => menu.classList.remove("open"));
  document.querySelectorAll(".mobile-link").forEach((l) => {
    l.addEventListener("click", () => menu.classList.remove("open"));
  });
})();

/* ── Smooth Scroll ────────────────────────────────────────────────────────── */
window.scrollToSection = function (id) {
  document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
};
window.scrollToTop = function () {
  window.scrollTo({ top: 0, behavior: "smooth" });
};

document.querySelectorAll('a[href^="#"]').forEach((a) => {
  a.addEventListener("click", (e) => {
    e.preventDefault();
    const target = document.querySelector(a.getAttribute("href"));
    if (target) target.scrollIntoView({ behavior: "smooth" });
  });
});

/* ── Intersection Observer (Reveal) ──────────────────────────────────────── */
const revealObserver = new IntersectionObserver(
  (entries) => {
    entries.forEach((entry, i) => {
      if (entry.isIntersecting) {
        entry.target.style.transitionDelay = i * 0.08 + "s";
        entry.target.classList.add("visible");
        revealObserver.unobserve(entry.target);
      }
    });
  },
  { threshold: 0.1, rootMargin: "0px 0px -60px 0px" },
);

function observeReveal(selector) {
  document.querySelectorAll(selector).forEach((el) => {
    el.classList.add("reveal");
    revealObserver.observe(el);
  });
}

/* ── Skill Bar Animation ──────────────────────────────────────────────────── */
const barObserver = new IntersectionObserver(
  (entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        const fill = entry.target.querySelector(".skill-fill");
        if (fill) {
          setTimeout(() => (fill.style.width = fill.dataset.level + "%"), 150);
        }
        barObserver.unobserve(entry.target);
      }
    });
  },
  { threshold: 0.3 },
);

/* ── Counter Animation ────────────────────────────────────────────────────── */
function animateCounter(el, end, suffix = "") {
  if (isNaN(parseInt(end))) {
    el.textContent = end;
    return;
  }
  const target = parseInt(end);
  let start = 0;
  const duration = 1800;
  const step = (timestamp) => {
    if (!start) start = timestamp;
    const progress = Math.min((timestamp - start) / duration, 1);
    const eased = 1 - Math.pow(1 - progress, 3);
    el.textContent = Math.floor(eased * target) + suffix;
    if (progress < 1) requestAnimationFrame(step);
  };
  requestAnimationFrame(step);
}

/* ── Load Profile ─────────────────────────────────────────────────────────── */
async function loadProfile() {
  try {
    const res = await fetch(API + "/api/profile");
    const data = await res.json();

    // Hero subtitle
    document.getElementById("heroSubtitle").textContent = data.bio;

    // Stats
    const statsEl = document.getElementById("heroStats");
    const stats = [
      { value: data.yearsExperience, label: "Years Exp", suffix: "+" },
      { value: data.projectsCompleted, label: "Projects", suffix: "+" },
      { value: data.clientsSatisfied, label: "Clients", suffix: "+" },
    ];
    statsEl.innerHTML = stats
      .map(
        (s) => `
      <div class="stat-item reveal">
        <span class="stat-number" data-target="${s.value}" data-suffix="${s.suffix}">0</span>
        <span class="stat-label">${s.label}</span>
      </div>
    `,
      )
      .join("");

    // Animate counters when visible
    const counterObs = new IntersectionObserver(
      (entries) => {
        entries.forEach((e) => {
          if (e.isIntersecting) {
            const el = e.target.querySelector(".stat-number");
            if (el) animateCounter(el, el.dataset.target, el.dataset.suffix);
            counterObs.unobserve(e.target);
          }
        });
      },
      { threshold: 0.5 },
    );
    statsEl
      .querySelectorAll(".stat-item")
      .forEach((el) => counterObs.observe(el));

    // Avatar initials
    const initials = data.name
      .split(" ")
      .map((n) => n[0])
      .join("");
    document.getElementById("avatarInitials").textContent = initials;

    // About bio
    document.getElementById("aboutBio").textContent = data.bio;

    // About details
    document.getElementById("aboutDetails").innerHTML = `
      <div class="detail-item reveal">
        <span class="detail-label">Location</span>
        <span class="detail-value">📍 ${data.location}</span>
      </div>
      <div class="detail-item reveal">
        <span class="detail-label">Email</span>
        <span class="detail-value">✉️ ${data.email}</span>
      </div>
      <div class="detail-item reveal">
        <span class="detail-label">Phone</span>
        <span class="detail-value">📞 ${data.phone}</span>
      </div>
      <div class="detail-item reveal">
        <span class="detail-label">Status</span>
        <span class="detail-value" style="color:var(--green)">🟢 Available</span>
      </div>
    `;

    // Social links
    document.getElementById("aboutSocial").innerHTML = `
      <a class="social-link" href="${data.github}" target="_blank" title="GitHub">
        <svg viewBox="0 0 24 24" width="18" height="18" fill="currentColor">
          <path d="M12 2C6.477 2 2 6.484 2 12.017c0 4.425 2.865 8.18 6.839 9.504.5.092.682-.217.682-.483 0-.237-.008-.868-.013-1.703-2.782.605-3.369-1.343-3.369-1.343-.454-1.158-1.11-1.466-1.11-1.466-.908-.62.069-.608.069-.608 1.003.07 1.531 1.032 1.531 1.032.892 1.53 2.341 1.088 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.113-4.555-4.951 0-1.093.39-1.988 1.029-2.688-.103-.253-.446-1.272.098-2.65 0 0 .84-.27 2.75 1.026A9.564 9.564 0 0112 6.844c.85.004 1.705.115 2.504.337 1.909-1.296 2.747-1.027 2.747-1.027.546 1.379.202 2.398.1 2.651.64.7 1.028 1.595 1.028 2.688 0 3.848-2.339 4.695-4.566 4.943.359.309.678.92.678 1.855 0 1.338-.012 2.419-.012 2.747 0 .268.18.58.688.482A10.019 10.019 0 0022 12.017C22 6.484 17.522 2 12 2z"/>
        </svg>
      </a>
      <a class="social-link" href="${data.linkedin}" target="_blank" title="LinkedIn">
        <svg viewBox="0 0 24 24" width="18" height="18" fill="currentColor">
          <path d="M20.447 20.452h-3.554v-5.569c0-1.328-.027-3.037-1.852-3.037-1.853 0-2.136 1.445-2.136 2.939v5.667H9.351V9h3.414v1.561h.046c.477-.9 1.637-1.85 3.37-1.85 3.601 0 4.267 2.37 4.267 5.455v6.286zM5.337 7.433a2.062 2.062 0 01-2.063-2.065 2.064 2.064 0 112.063 2.065zm1.782 13.019H3.555V9h3.564v11.452zM22.225 0H1.771C.792 0 0 .774 0 1.729v20.542C0 23.227.792 24 1.771 24h20.451C23.2 24 24 23.227 24 22.271V1.729C24 .774 23.2 0 22.222 0h.003z"/>
        </svg>
      </a>
      <a class="social-link" href="${data.twitter}" target="_blank" title="Twitter">
        <svg viewBox="0 0 24 24" width="18" height="18" fill="currentColor">
          <path d="M18.244 2.25h3.308l-7.227 8.26 8.502 11.24H16.17l-4.714-6.231-5.401 6.231H2.74l7.73-8.835L1.254 2.25H8.08l4.253 5.622zm-1.161 17.52h1.833L7.084 4.126H5.117z"/>
        </svg>
      </a>
    `;

    // Contact links
    document.getElementById("contactLinks").innerHTML = `
      <a class="contact-link-item" href="mailto:${data.email}">
        <span class="contact-link-icon">✉️</span>
        <div>
          <div class="contact-link-label">Email</div>
          <div class="contact-link-value">${data.email}</div>
        </div>
      </a>
      <a class="contact-link-item" href="tel:${data.phone}">
        <span class="contact-link-icon">📞</span>
        <div>
          <div class="contact-link-label">Phone</div>
          <div class="contact-link-value">${data.phone}</div>
        </div>
      </a>
      <a class="contact-link-item" href="${data.github}" target="_blank">
        <span class="contact-link-icon">🐙</span>
        <div>
          <div class="contact-link-label">GitHub</div>
          <div class="contact-link-value">Abhishekmaheshwari-2002</div>
        </div>
      </a>
      <div class="contact-link-item">
        <span class="contact-link-icon">📍</span>
        <div>
          <div class="contact-link-label">Location</div>
          <div class="contact-link-value">${data.location}</div>
        </div>
      </div>
    `;

    observeReveal(".detail-item");
  } catch (err) {
    console.error("Profile load error:", err);
  }
}

/* ── Load Skills ──────────────────────────────────────────────────────────── */
async function loadSkills() {
  try {
    const res = await fetch(API + "/api/skills");
    const skills = await res.json();
    const grid = document.getElementById("skillsGrid");
    let currentFilter = "All";

    function renderSkills(filter) {
      const filtered =
        filter === "All" ? skills : skills.filter((s) => s.category === filter);
      grid.innerHTML = filtered
        .map(
          (skill) => `
        <div class="skill-card reveal" data-category="${skill.category}">
          <div class="skill-header">
            <div class="skill-name-wrap">
              <span class="skill-icon">${skill.icon}</span>
              <span class="skill-name">${skill.name}</span>
            </div>
            <span class="skill-level-text">${skill.level}%</span>
          </div>
          <div class="skill-bar">
            <div class="skill-fill" data-level="${skill.level}"></div>
          </div>
          <span class="skill-category">${skill.category}</span>
        </div>
      `,
        )
        .join("");

      document.querySelectorAll(".skill-card").forEach((el) => {
        el.classList.add("reveal");
        revealObserver.observe(el);
        barObserver.observe(el);
      });
    }

    renderSkills("All");

    document.querySelectorAll("#skillsFilter .filter-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        document
          .querySelectorAll("#skillsFilter .filter-btn")
          .forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
        currentFilter = btn.dataset.filter;
        renderSkills(currentFilter);
      });
    });
  } catch (err) {
    console.error("Skills load error:", err);
  }
}

/* ── Load Projects ────────────────────────────────────────────────────────── */
async function loadProjects() {
  try {
    const res = await fetch(API + "/api/projects");
    const projects = await res.json();
    const grid = document.getElementById("projectsGrid");

    function renderProjects(filter) {
      const filtered =
        filter === "All"
          ? projects
          : projects.filter((p) => p.category === filter);
      grid.innerHTML = filtered
        .map(
          (proj) => `
        <div class="project-card reveal" data-category="${proj.category}">
          <div class="project-header">
            <span class="project-year">${proj.year}</span>
            <span class="project-category">${proj.category}</span>
          </div>
          <h3 class="project-title">${proj.title}</h3>
          <p class="project-desc">${proj.description}</p>
          <div class="project-tech">
            ${proj.technologies.map((t) => `<span class="tech-tag">${t}</span>`).join("")}
          </div>
          <div class="project-links">
            ${
              proj.githubUrl
                ? `
              <a class="proj-link" href="${proj.githubUrl}" target="_blank">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 00-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0020 4.77 5.07 5.07 0 0019.91 1S18.73.65 16 2.48a13.38 13.38 0 00-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 005 4.77a5.44 5.44 0 00-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 009 18.13V22"/>
                </svg>
                Source Code
              </a>
            `
                : ""
            }
            ${
              proj.liveUrl
                ? `
              <a class="proj-link" href="${proj.liveUrl}" target="_blank">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M18 13v6a2 2 0 01-2 2H5a2 2 0 01-2-2V8a2 2 0 012-2h6M15 3h6v6M10 14L21 3"/>
                </svg>
                Live Demo
              </a>
            `
                : ""
            }
          </div>
        </div>
      `,
        )
        .join("");

      document.querySelectorAll(".project-card").forEach((el) => {
        el.classList.add("reveal");
        revealObserver.observe(el);
      });
    }

    renderProjects("All");

    document.querySelectorAll("#projectsFilter .filter-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        document
          .querySelectorAll("#projectsFilter .filter-btn")
          .forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
        renderProjects(btn.dataset.filter);
      });
    });
  } catch (err) {
    console.error("Projects load error:", err);
  }
}

/* ── Load Experience ──────────────────────────────────────────────────────── */
async function loadExperience() {
  try {
    const res = await fetch(API + "/api/experience");
    const experiences = await res.json();
    const emojis = ["🏢", "🚀", "💼"];
    document.getElementById("timeline").innerHTML = experiences
      .map(
        (exp, i) => `
      <div class="timeline-item reveal">
        <div class="timeline-dot">${emojis[i] || "💻"}</div>
        <div class="timeline-content">
          <div class="timeline-meta">
            <span class="timeline-company">${exp.company}</span>
            <span class="timeline-period">📅 ${exp.period}</span>
          </div>
          <h3 class="timeline-role">${exp.role}</h3>
          <p class="timeline-desc">${exp.description}</p>
          <div class="timeline-achievements">
            ${exp.achievements
              .map(
                (a) => `
              <div class="achievement">${a}</div>
            `,
              )
              .join("")}
          </div>
        </div>
      </div>
    `,
      )
      .join("");
    observeReveal(".timeline-item");
  } catch (err) {
    console.error("Experience load error:", err);
  }
}

/* ── Contact Form ─────────────────────────────────────────────────────────── */
window.handleFormSubmit = async function (e) {
  e.preventDefault();
  const btn = document.getElementById("submitBtn");
  const feedback = document.getElementById("formFeedback");
  const form = document.getElementById("contactForm");

  btn.classList.add("btn-loading");
  btn.disabled = true;
  feedback.className = "form-feedback";
  feedback.style.display = "none";

  const payload = {
    name: form.name.value.trim(),
    email: form.email.value.trim(),
    subject: form.subject.value.trim(),
    message: form.message.value.trim(),
  };

  try {
    const res = await fetch(API + "/api/contact", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    const data = await res.json();

    if (data.status === "success") {
      feedback.textContent = "✅ " + data.message;
      feedback.className = "form-feedback success";
      form.reset();
    } else {
      throw new Error("Unexpected response");
    }
  } catch (err) {
    feedback.textContent =
      "❌ Something went wrong. Please try again or email directly.";
    feedback.className = "form-feedback error";
  } finally {
    btn.classList.remove("btn-loading");
    btn.disabled = false;
  }
};

/* ── Init ─────────────────────────────────────────────────────────────────── */
document.addEventListener("DOMContentLoaded", async () => {
  await Promise.all([
    loadProfile(),
    loadSkills(),
    loadProjects(),
    loadExperience(),
  ]);
  // Observe static reveal elements
  observeReveal(
    ".section-header, .about-avatar-wrap, .about-text, .contact-info, .contact-form",
  );
});
