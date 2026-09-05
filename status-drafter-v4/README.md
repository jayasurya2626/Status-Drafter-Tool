# Status Drafter Tool

A Spring Boot + Thymeleaf + MySQL task/status management application with EMPLOYEE, MANAGER and ADMIN roles.

## Stack
- Java 21
- Spring Boot 3.5.0
- Spring MVC / Spring Data JPA / Hibernate
- Spring Security + BCrypt + session authentication
- Thymeleaf + HTML5/CSS3/JavaScript
- MySQL / MySQL Workbench
- Maven

## Database setup (MySQL Workbench)
1. Open MySQL Workbench and connect to your MySQL server.
2. Open `database/schema.sql` and run it. It creates the `status_drafter` database.
3. Set the database credentials before starting the app. Environment variables are recommended:
   - `DB_URL=jdbc:mysql://localhost:3306/status_drafter?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true`
   - `DB_USERNAME=root`
   - `DB_PASSWORD=your_mysql_password`
4. The application uses `spring.jpa.hibernate.ddl-auto=update`, so JPA creates/updates the tables and foreign keys from the entity mappings.

### application.properties fallback
The default username is `root` and default password is `root`. Change these values or, preferably, use environment variables. Never commit real production credentials.

## Run
From the project root:

```bash
mvn clean spring-boot:run
```

Or build a jar:

```bash
mvn clean package
java -jar target/status-drafter-tool-1.0.0.jar
```

Open `http://localhost:8080/login`.

## Default admin
On first startup, `DataInitializer` creates an admin if the configured admin username does not exist.

Default values:
- Username: `admin`
- Password: `Admin@123`
- Email: `admin@example.com`

Override with `ADMIN_USERNAME`, `ADMIN_PASSWORD`, `ADMIN_NAME`, and `ADMIN_EMAIL` environment variables before first startup.

## Create other roles
- Public registration creates EMPLOYEE accounts.
- Log in as ADMIN and use **Users** to create MANAGER/ADMIN/EMPLOYEE users. For a new user, provide a password and role.

## URLs
### Authentication
- `/login`
- `/register`
- `/forgot-password`

### Employee
- `/employee/dashboard`
- `/employee/tasks`
- `/employee/tasks/add`
- `/employee/tasks/edit/{id}`
- `/employee/tasks/{id}`
- `/employee/status`
- `/employee/status/edit/{id}`
- `/employee/status-history`
- `/employee/status-generator`
- `/employee/deadlines`
- `/employee/notifications`
- `/employee/reports?period=daily|weekly|monthly`
- `/employee/profile`

### Manager
- `/manager/dashboard`
- `/manager/employees`
- `/manager/employee-tasks`
- `/manager/status-updates`
- `/manager/deadlines`
- `/manager/reports`

### Admin
- `/admin/dashboard`
- `/admin/users`
- `/admin/tasks`
- `/admin/reports`

## Functional notes
- Employee task search/filter is executed through a Spring Data JPA query, not a client-side dummy array.
- Dashboard counts are calculated from repository queries.
- The status generator groups real employee tasks by their current status and permits selecting specific tasks.
- Task status transitions are stored in `status_history`.
- Notifications are persisted and generated from actual approaching/overdue tasks and missing daily status updates.
- BCrypt is used before passwords are stored.
- Role URL rules are enforced by Spring Security, so changing a URL manually does not bypass authorization.

## Testing checklist
See `docs/verification.md` for the page/button verification matrix.

## Important environment limitation
This source package is generated as a complete Maven project. The execution environment used to assemble the zip does not have Maven installed and has no network/DNS access to download Maven dependencies, so a live MySQL integration run could not be performed here. Run `mvn clean package` on a machine with Maven and MySQL to perform the final environment-specific build/integration check.
