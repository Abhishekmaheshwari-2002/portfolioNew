package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.model.Project;
import com.portfolio.model.Skill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PortfolioController {

    // ─── Projects ─────────────────────────────────────────────────────────────
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = new ArrayList<>();

        projects.add(new Project(
                "1",
                "E-Commerce Platform",
                "A full-featured online store built with microservices architecture. Handles 10k+ concurrent users with real-time inventory management, payment gateway integration, and advanced analytics dashboard.",
                new String[] { "Java", "Spring Boot", "React", "PostgreSQL", "Redis", "Docker" },
                "Backend",
                "https://github.com/yourusername/ecommerce",
                "https://ecommerce-demo.com",
                "2024"));

        projects.add(new Project(
                "2",
                "AI Chat Application",
                "Real-time conversational AI platform using WebSockets and NLP. Features multi-room support, message history, sentiment analysis, and smart auto-replies powered by machine learning models.",
                new String[] { "Java", "WebSocket", "Python", "TensorFlow", "MongoDB", "Vue.js" },
                "AI/ML",
                "https://github.com/yourusername/ai-chat",
                "https://ai-chat-demo.com",
                "2024"));

        projects.add(new Project(
                "3",
                "DevOps Pipeline Dashboard",
                "Unified CI/CD monitoring dashboard aggregating data from GitHub Actions, Jenkins, and Kubernetes clusters. Visualizes deployment metrics, alerts, and rollback controls in real-time.",
                new String[] { "Java", "Spring Boot", "Kubernetes", "Prometheus", "Grafana", "Angular" },
                "DevOps",
                "https://github.com/yourusername/devops-dashboard",
                "https://devops-demo.com",
                "2023"));

        projects.add(new Project(
                "4",
                "Financial Analytics API",
                "High-performance REST API processing financial transactions at scale. Implements CQRS pattern, event sourcing, and provides real-time fraud detection with 99.99% uptime SLA.",
                new String[] { "Java", "Spring Boot", "Kafka", "Elasticsearch", "MySQL", "Swagger" },
                "Backend",
                "https://github.com/yourusername/finance-api",
                "https://finance-api-docs.com",
                "2023"));

        projects.add(new Project(
                "5",
                "Social Media Analytics",
                "Data pipeline ingesting millions of social posts daily. Provides trend analysis, influencer scoring, hashtag tracking, and competitive intelligence with beautiful visualization charts.",
                new String[] { "Java", "Apache Spark", "Hadoop", "React", "D3.js", "AWS" },
                "Data Engineering",
                "https://github.com/yourusername/social-analytics",
                "",
                "2023"));

        projects.add(new Project(
                "6",
                "Mobile Banking App",
                "Secure banking application with biometric authentication, real-time transfers, spending insights, and investment portfolio tracking. Compliant with PCI-DSS and GDPR regulations.",
                new String[] { "Java", "Spring Security", "React Native", "PostgreSQL", "JWT", "OAuth2" },
                "FinTech",
                "https://github.com/yourusername/mobile-banking",
                "",
                "2022"));

        return ResponseEntity.ok(projects);
    }

    // ─── Skills ───────────────────────────────────────────────────────────────
    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getSkills() {
        List<Skill> skills = new ArrayList<>();

        // Backend
        skills.add(new Skill("Java", 95, "Backend", "☕"));
        skills.add(new Skill("Spring Boot", 92, "Backend", "🍃"));
        skills.add(new Skill("Python", 80, "Backend", "🐍"));
        skills.add(new Skill("Node.js", 75, "Backend", "🟢"));
        skills.add(new Skill("REST API Design", 90, "Backend", "🔌"));
        skills.add(new Skill("Microservices", 88, "Backend", "⚙️"));

        // Frontend
        skills.add(new Skill("React", 82, "Frontend", "⚛️"));
        skills.add(new Skill("JavaScript", 85, "Frontend", "🌐"));
        skills.add(new Skill("TypeScript", 78, "Frontend", "📘"));
        skills.add(new Skill("HTML/CSS", 88, "Frontend", "🎨"));

        // Database
        skills.add(new Skill("PostgreSQL", 87, "Database", "🐘"));
        skills.add(new Skill("MongoDB", 80, "Database", "🍃"));
        skills.add(new Skill("Redis", 75, "Database", "🔴"));
        skills.add(new Skill("MySQL", 85, "Database", "🗄️"));

        // DevOps / Cloud
        skills.add(new Skill("Docker", 85, "DevOps", "🐋"));
        skills.add(new Skill("Kubernetes", 75, "DevOps", "☸️"));
        skills.add(new Skill("AWS", 78, "DevOps", "☁️"));
        skills.add(new Skill("CI/CD", 82, "DevOps", "🔄"));

        return ResponseEntity.ok(skills);
    }

    // ─── Profile ──────────────────────────────────────────────────────────────
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile() {
        Map<String, Object> profile = new LinkedHashMap<>();
        profile.put("name", "Alex Johnson");
        profile.put("title", "Senior Full-Stack Java Developer");
        profile.put("tagline", "I build scalable systems that make a difference.");
        profile.put("bio",
                "Passionate software engineer with years of experience designing and building high-performance backend systems. I specialize in Java/Spring Boot, cloud-native architectures, and turning complex problems into elegant, maintainable solutions.");
        profile.put("email", "alex@example.com");
        profile.put("phone", "+1 (555) 123-4567");
        profile.put("location", "San Francisco, CA");
        profile.put("github", "https://github.com/yourusername");
        profile.put("linkedin", "https://linkedin.com/in/yourusername");
        profile.put("twitter", "https://twitter.com/yourusername");
        profile.put("yearsExperience", 6);
        profile.put("projectsCompleted", 40);
        profile.put("clientsSatisfied", 25);
        profile.put("coffeeConsumed", "∞");
        return ResponseEntity.ok(profile);
    }

    // ─── Experience ───────────────────────────────────────────────────────────
    @GetMapping("/experience")
    public ResponseEntity<List<Map<String, Object>>> getExperience() {
        List<Map<String, Object>> experiences = new ArrayList<>();

        Map<String, Object> exp1 = new LinkedHashMap<>();
        exp1.put("company", "TechCorp Inc.");
        exp1.put("role", "Senior Software Engineer");
        exp1.put("period", "2022 – Present");
        exp1.put("location", "San Francisco, CA");
        exp1.put("description",
                "Lead backend development for a platform serving 2M+ users. Architected microservices migration cutting infrastructure costs by 40%. Mentored a team of 5 junior developers.");
        exp1.put("achievements", new String[] {
                "Reduced API response time by 60% through Redis caching strategy",
                "Led migration from monolith to microservices (zero downtime)",
                "Implemented event-driven architecture with Apache Kafka"
        });
        experiences.add(exp1);

        Map<String, Object> exp2 = new LinkedHashMap<>();
        exp2.put("company", "StartupXYZ");
        exp2.put("role", "Full-Stack Developer");
        exp2.put("period", "2020 – 2022");
        exp2.put("location", "Remote");
        exp2.put("description",
                "Built and shipped 3 SaaS products from concept to production. Responsible for full-stack development, DevOps pipeline setup, and database architecture decisions.");
        exp2.put("achievements", new String[] {
                "Delivered MVP in 6 weeks, securing $2M seed funding",
                "Built real-time notification system handling 100k+ daily events",
                "Set up CI/CD pipelines reducing deployment time by 80%"
        });
        experiences.add(exp2);

        Map<String, Object> exp3 = new LinkedHashMap<>();
        exp3.put("company", "Digital Agency Pro");
        exp3.put("role", "Junior Java Developer");
        exp3.put("period", "2018 – 2020");
        exp3.put("location", "New York, NY");
        exp3.put("description",
                "Developed enterprise-level web applications for Fortune 500 clients. Gained deep expertise in Spring Boot, Hibernate, and RESTful API design patterns.");
        exp3.put("achievements", new String[] {
                "Developed banking portal used by 500k+ customers",
                "Optimized database queries improving performance by 45%",
                "Integrated 10+ third-party payment and analytics APIs"
        });
        experiences.add(exp3);

        return ResponseEntity.ok(experiences);
    }

    // ─── Contact ──────────────────────────────────────────────────────────────
    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody ContactMessage message) {
        // In production: save to DB, send email, etc.
        System.out.println("📬 New message from: " + message.getName() + " <" + message.getEmail() + ">");
        System.out.println("Subject: " + message.getSubject());
        System.out.println("Message: " + message.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Thank you " + message.getName()
                + "! Your message has been received. I'll get back to you within 24 hours.");
        return ResponseEntity.ok(response);
    }
}
