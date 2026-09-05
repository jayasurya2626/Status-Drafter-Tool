# Page-by-page verification matrix

| Page | URL | Controller | Main function | Database used | Working path implemented |
|---|---|---|---|---|---|
| Login | `/login` | AuthController/Spring Security | Session login | users | Form -> Spring Security -> users |
| Register | `/register` | AuthController | Employee registration | users | Form -> UserService -> UserRepository |
| Employee Dashboard | `/employee/dashboard` | EmployeeController | Counts/deadlines/statuses | tasks/status_updates/notifications | Controller -> services -> repositories |
| My Tasks | `/employee/tasks` | EmployeeController | Search/filter/list | tasks/users | GET filters -> TaskRepository.search |
| Add Task | `/employee/tasks/add` | EmployeeController | Create task | tasks | POST -> TaskService -> TaskRepository |
| Edit Task | `/employee/tasks/edit/{id}` | EmployeeController | Update task/status | tasks/status_history | POST -> TaskService -> repositories |
| Task Details | `/employee/tasks/{id}` | EmployeeController | Read one task | tasks | GET -> TaskRepository |
| Daily Status | `/employee/status` | EmployeeController | Create/update daily status | status_updates | POST -> StatusUpdateService -> repository |
| Status History | `/employee/status-history` | EmployeeController | History CRUD/list | status_updates | GET/POST -> repository |
| Status Generator | `/employee/status-generator` | EmployeeController | Generate from selected real tasks | tasks/status_updates | POST IDs -> StatusGeneratorService |
| Deadlines | `/employee/deadlines` | EmployeeController | Upcoming/overdue | tasks | Repository date queries |
| Notifications | `/employee/notifications` | EmployeeController | Persistent reminders/read state | notifications/tasks/status_updates | NotificationService -> repositories |
| Reports | `/employee/reports` | EmployeeController | Counts + daily/weekly/monthly activity | tasks | ReportService -> repositories |
| Profile | `/employee/profile` | EmployeeController | Update profile | users | POST -> UserRepository |
| Manager Dashboard | `/manager/dashboard` | ManagerController | Overall progress | users/tasks | ReportService -> repositories |
| Employees | `/manager/employees` | ManagerController | Employee list | users | UserRepository |
| Employee Tasks | `/manager/employee-tasks` | ManagerController | Employee task monitoring | users/tasks | Repository-backed |
| Status Updates | `/manager/status-updates` | ManagerController | Review status updates | users/status_updates | Repository-backed |
| Manager Deadlines | `/manager/deadlines` | ManagerController | Overdue monitoring | tasks | Repository-backed |
| Manager Reports | `/manager/reports` | ManagerController | Overall report | users/tasks | ReportService |
| Admin Dashboard | `/admin/dashboard` | AdminController | Overall metrics | users/tasks | ReportService |
| Users | `/admin/users` | AdminController | Create/update/delete/deactivate users | users | POST -> UserService/UserRepository |
| All Tasks | `/admin/tasks` | AdminController | Search/filter all tasks | tasks/users | JPA query |
| Admin Reports | `/admin/reports` | AdminController | Overall report | users/tasks | ReportService |

# Important button verification

| Button/action | Endpoint | DB operation/result |
|---|---|---|
| Login | POST `/login` | Spring Security authenticates against users |
| Register | POST `/register` | INSERT user with BCrypt password |
| Create Task | POST `/employee/tasks/add` | INSERT tasks |
| Edit Task | POST `/employee/tasks/edit/{id}` | UPDATE tasks + optional INSERT status_history |
| Delete Task | POST `/employee/tasks/delete/{id}` | DELETE task |
| Save Daily Status | POST `/employee/status` | INSERT/UPDATE status_updates |
| Edit Status | POST `/employee/status/edit/{id}` | UPDATE status_updates |
| Delete Status | POST `/employee/status/delete/{id}` | DELETE status_updates |
| Generate Status | POST `/employee/status-generator/generate` | SELECT tasks; generated text returned to page |
| Save Generated Status | POST `/employee/status-generator/save` | INSERT/UPDATE status_updates |
| Mark Notification Read | POST `/employee/notifications/read/{id}` | UPDATE notifications.read_status |
| Update Profile | POST `/employee/profile` | UPDATE users |
| Admin Create User | POST `/admin/users` | INSERT users with BCrypt |
| Admin Update User | POST `/admin/users` | UPDATE users |
| Admin Delete User | POST `/admin/users/delete/{id}` | DELETE users |
