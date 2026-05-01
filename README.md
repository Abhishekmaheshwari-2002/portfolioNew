# 🚀 Portfolio Website — Java + Spring Boot

A full-stack portfolio website built with **Java Spring Boot** backend and a stunning dark-themed frontend.

---

## 📁 Project Structure

```
portfolio/
├── .vscode/
│   ├── launch.json          # VS Code run/debug configs
│   ├── settings.json        # Java/formatting settings
│   └── extensions.json      # Recommended extensions
├── src/
│   └── main/
│       ├── java/com/portfolio/
│       │   ├── PortfolioApplication.java     # Entry point
│       │   ├── controller/
│       │   │   └── PortfolioController.java  # REST API endpoints
│       │   └── model/
│       │       ├── Project.java
│       │       ├── Skill.java
│       │       └── ContactMessage.java
│       └── resources/
│           ├── application.properties
│           └── static/
│               ├── index.html               # Main UI
│               ├── css/style.css            # Styles
│               └── js/main.js               # Frontend logic
└── pom.xml                                  # Maven dependencies
```

---

## ⚙️ Prerequisites

| Tool | Version |
|------|---------|
| Java | 17+ |
| Maven | 3.8+ |
| VS Code | Latest |

---

## 🛠️ Setup in VS Code

### 1. Install VS Code Extensions
Open VS Code, press `Ctrl+Shift+P` → **"Extensions: Show Recommended Extensions"** → Install all.

Or install manually:
- **Extension Pack for Java** (`vscjava.vscode-java-pack`)
- **Spring Boot Extension Pack** (`vmware.vscode-spring-boot`)
- **Spring Boot Dashboard** (`vscjava.vscode-spring-boot-dashboard`)

### 2. Open the Project
```bash
code portfolio/
```

### 3. Run the Application

**Option A — VS Code Run Button:**
- Open `PortfolioApplication.java`
- Click the ▶️ **Run** button above `main()`

**Option B — VS Code Launch Config:**
- Press `F5` or go to **Run → Start Debugging**
- Select **"Run Portfolio App"**

**Option C — Spring Boot Dashboard:**
- Open the Spring Boot Dashboard panel (sidebar)
- Click the ▶️ next to `portfolio-website`

**Option D — Terminal:**
```bash
cd portfolio
./mvnw spring-boot:run
```
*(Windows: `mvnw.cmd spring-boot:run`)*

---

## 🌐 Access the Website

Open your browser: **http://localhost:8080**

---

## 🔌 REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/profile` | Developer profile & stats |
| `GET` | `/api/projects` | All projects (filterable) |
| `GET` | `/api/skills` | Tech skills with levels |
| `GET` | `/api/experience` | Work experience timeline |
| `POST` | `/api/contact` | Submit contact message |

### Example API call:
```bash
curl http://localhost:8080/api/profile
curl http://localhost:8080/api/projects
```

### Contact POST example:
```bash
curl -X POST http://localhost:8080/api/contact \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","subject":"Hello","message":"Hi!"}'
```

---

## ✏️ Customization

### Update Your Info
Edit `PortfolioController.java` → `getProfile()` method:
```java
profile.put("name", "Your Name");
profile.put("email", "your@email.com");
profile.put("location", "Your City");
// ... etc.
```

### Add Projects
Edit `getProjects()` method — add a new `Project(...)` object.

### Add Skills
Edit `getSkills()` method — add a new `Skill(...)` object.

### Change Theme Colors
Edit `src/main/resources/static/css/style.css` → `:root` variables:
```css
--accent: #6c63ff;      /* Purple — change to your preferred color */
--bg: #090a0f;          /* Dark background */
```

---

## 🚀 Build for Production

```bash
./mvnw clean package -DskipTests
java -jar target/portfolio-website-1.0.0.jar
```

---

## 📱 Features

- ✅ Responsive design (mobile-first)
- ✅ Dark editorial aesthetic
- ✅ Custom animated cursor
- ✅ Smooth scroll & reveal animations
- ✅ Skill bars with animated fill
- ✅ Project & skill category filters
- ✅ Experience timeline
- ✅ Contact form with Spring Boot backend
- ✅ REST API architecture
- ✅ Spring Boot DevTools (live reload)
- ✅ VS Code fully configured

---

*Built with ☕ Java + Spring Boot*
