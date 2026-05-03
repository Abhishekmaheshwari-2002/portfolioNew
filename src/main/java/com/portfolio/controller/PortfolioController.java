package com.portfolio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.ContactMessage;
import com.portfolio.model.Project;
import com.portfolio.model.Skill;

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
                                "Advanced Hospital Analytics Dashboard – Power BI Project",
                                "Built an interactive Power BI dashboard that transforms hospital data into actionable insights—covering patients, finances, inventory, and staff performance in one unified view.",
                                new String[] { "Power BI", "Advanced Excel", "SQL", "Power Query" },
                                "Dashboard",
                                "",
                                "https://hospital-dashboard-eosin.vercel.app/",
                                "2025"));

                projects.add(new Project(
                                "2",
                                "Lucky Roll",
                                "Lucky Roll is an interactive web application that simulates virtual dice rolls using JavaScript, enabling users to generate random outcomes for games or quick decisions with a simple and engaging interface.",
                                new String[] { "HTML", "Tailwind CSS", "React.js", "JavaScript", "Next.js" },
                                "Frontend",
                                "https://github.com/Abhishekmaheshwari-2002/Roll-dice-Game",
                                "https://roll-dice-ten.vercel.app/",
                                "2025"));

                projects.add(new Project(
                                "3",
                                "TextUtils – Text Processing Web Application",
                                "Developed a responsive text processing application using React.js, implementing features like case conversion, word count analysis, and text formatting with real-time updates using state management and efficient component rendering.",
                                new String[] { "HTML", "CSS", "JavaScript", "React", "Bootstrap" },
                                "Frontend",
                                "https://github.com/Abhishekmaheshwari-2002/TextUtil.github.io",
                                "https://text-util-sigma.vercel.app/",
                                "2024"));

                projects.add(new Project(
                                "4",
                                "Thief Detection System (AI-Based Web Application)",
                                "Designed and implemented a real-time theft detection system using Next.js and COCO-SSD for object detection, integrated with React Webcam for live video streaming. Enabled accurate detection of objects and suspicious activities through real-time processing and responsive UI.",
                                new String[] { "HTML", "CSS", "JavaScript", "Next.js", "Tailwind CSS" },
                                "AI/ML",
                                "https://github.com/Abhishekmaheshwari-2002/thief-detect",
                                "https://thief-detect.vercel.app/",
                                "2024"));

                return ResponseEntity.ok(projects);
        }

        // ─── Skills ───────────────────────────────────────────────────────────────
        @GetMapping("/skills")
        public ResponseEntity<List<Skill>> getSkills() {
                List<Skill> skills = new ArrayList<>();

                // Backend
                skills.add(new Skill("Java", 95, "Backend", "☕"));
                skills.add(new Skill("Python", 80, "Backend", "🐍"));
                skills.add(new Skill("Node.js", 75, "Backend", "🟢"));

                // Frontend
                skills.add(new Skill("Next.Js", 82, "Frontend", "⚛️"));
                skills.add(new Skill("React", 82, "Frontend", "⚛️"));
                skills.add(new Skill("JavaScript", 85, "Frontend", "🌐"));
                skills.add(new Skill("HTML/CSS", 90, "Frontend", "🎨"));

                // Database
                skills.add(new Skill("MongoDB", 80, "Database", "🍃"));
                skills.add(new Skill("MySQL", 85, "Database", "🗄️"));

                return ResponseEntity.ok(skills);
        }

        // ─── Profile ──────────────────────────────────────────────────────────────
        @GetMapping("/profile")
        public ResponseEntity<Map<String, Object>> getProfile() {
                Map<String, Object> profile = new LinkedHashMap<>();
                profile.put("name", "Abhishek Maheshwari");
                profile.put("title", "Software Developer");
                profile.put("tagline", "I build scalable systems that make a difference.");
                profile.put("bio",
                                "I turn ideas into scalable, user-centric web applications using React and Next.js. With a strong foundation in JavaScript, OOP, and modern web technologies, I craft clean, responsive experiences while solving complex problems with simplicity. I also explore data using Python, Excel, and Power BI to uncover insights that drive smarter decisions.");
                profile.put("email", "abhishek20602@gmail.com");
                profile.put("phone", "8962630785");
                profile.put("location", "Gurgaon, Haryana");
                profile.put("github", "https://github.com/Abhishekmaheshwari-2002");
                profile.put("linkedin", "https://www.linkedin.com/in/abhishek-maheshwari-602344226/");

                profile.put("twitter", "https://twitter.com/yourusername");
                profile.put("yearsExperience", 1);

                return ResponseEntity.ok(profile);
        }

        // ─── Experience ───────────────────────────────────────────────────────────
        @GetMapping("/experience")
        public ResponseEntity<List<Map<String, Object>>> getExperience() {
                List<Map<String, Object>> experiences = new ArrayList<>();

                Map<String, Object> exp1 = new LinkedHashMap<>();
                exp1.put("company", "PeopleStrong");
                exp1.put("role", "Product Analyst");
                exp1.put("period", "August 2025 – Present");
                exp1.put("location", "Gurgaon, Haryana");
                exp1.put("description",
                                "Managed and resolved 300+ monthly production support tickets with 95–98% SLA adherence, ensuring high application\r\n"
                                                + //
                                                "availability and client satisfaction.\r\n" + //
                                                "•Reduced average resolution time by 30% through structured root cause analysis, SQL-driven backend investigation, and\r\n"
                                                + //
                                                "efficient incident handling.\r\n" + //
                                                "");

                exp1.put("achievements", new String[] {
                                "Performed incident trend analysis to identify recurring system defects",

                                "Collaborated with Product and Engineering teams to communicate defects and enhancement requests using data-backed insights, supporting prioritization and faster releases"
                });
                experiences.add(exp1);

                Map<String, Object> exp2 = new LinkedHashMap<>();
                exp2.put("company", "Qualitas Global");
                exp2.put("role", "Junior Gen AI Intern");
                exp2.put("period", "May 2025 – July 2025");
                exp2.put("location", "Pune, Maharashtra");
                exp2.put("description",
                                "Prepared and structured labelled datasets to support computer vision model training pipelines");
                exp2.put("achievements", new String[] {
                                "Conducted data validation and quality audits to ensure annotation accuracy across large datasets.",
                                "Collaborated with AI engineers to resolve dataset inconsistencies, improving model training reliability."
                });
                experiences.add(exp2);

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
