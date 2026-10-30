# BASE BUS TRACKING

## Security Test Case Procedure

| ITEM# | TEST ITEMS | FEATURES TO BE TESTED | PERSONS INVOLVE |
| :---: | :--- | :--- | :--- |
| **1** | **Sign Up Form Access** | *The 'Sign Up' link on the login screen navigates successfully to the registration form.* | QA, Programmer, Project Manager |
| **2** | **Gmail Domain Enforcement** | *The system validates that registration is restricted exclusively to valid `@gmail.com` addresses.* | QA, Programmer, Project Manager |
| **3** | **Required Field Check** | *The system checks for any blank textboxes (Full Name, Email, Password) when submitting the sign-up form.* | QA, Programmer, Project Manager |
| **4** | **Password Masking** | *The password input is hidden (masked as bullet characters) by default during credential entry.* | QA, Programmer, Project Manager |
| **5** | **Email Verification Dispatch** | *Firebase Auth automatically triggers a verification email/code to the user's Gmail inbox upon successful registration.* | QA, Programmer, Project Manager |
| **6** | **Login Credential Entry** | *Users can enter their verified Gmail credentials on the login form securely.* | QA, Programmer, Project Manager |
| **7** | **Password Visibility Toggle** | *The show/hide eye icon toggle allows securely previewing or masking the entered password on login.* | QA, Programmer, Project Manager |
| **8** | **Knowledge-Based Security (KBS) Challenge** | *The system intercepts successful credentials and enforces the security question challenge before granting dashboard access.* | QA, Programmer, Project Manager |
| **9** | **Secret Answer Validation** | *The system validates the user's secret answer (case-insensitive) against the secure database record.* | QA, Programmer, Project Manager |
| **10** | **In-App Account & Data Deletion** | *The 'Delete My Data' action in profile settings permanently purges database records and deletes the authentication account.* | QA, Programmer, Project Manager |
| **11** | **Deletion Audit Logging** | *The system records a secure deletion audit log in `deletion_logs` prior to account and data purging.* | QA, Programmer, Project Manager |
