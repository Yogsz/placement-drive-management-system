import { useState } from "react";
import { useNavigate } from "react-router-dom";
import apiRequest from "../services/api";

function Auth() {
  const [mode, setMode] = useState("login");
  const [role, setRole] = useState("student");

  const changeMode = () => setMode(mode === "login" ? "register" : "login");

  const changeRole = () => setRole(role === "student" ? "company" : "student");

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (email === "") {
      alert("Please enter your email");
      return;
    }

    if (password === "") {
      alert("Please enter your password");
      return;
    }
    const response = await fetch("http://localhost:8080/api/auth/login", {
      method: "Post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    });
    const token = await response.text();
    const role = getRoleFromToken(token);
    console.log(role);
    if (token === "Login Failed") {
      alert("Invalid Email or Password");
      return;
    }
    
    localStorage.setItem("token", token);

    if(role === "STUDENT"){
      navigate("/dashboard");
    }

    console.log("Email:", email);
    console.log("Password:", password);
  };

  const getRoleFromToken = (token) => {
    const payload = token.split(".")[1];
    const decodePayLoad = atob(payload);
    const data = JSON.parse(decodePayLoad);

    return data.role;
  }
  const getStudents = async () => {
    const response = await fetch(
        "http://localhost:8080/api/students",
        {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("token")}`
            }
        }
    );

    const data = await response.json();

    console.log(data);
};
  


  const [studentName, setStudentName] = useState("");
  const [studentEmail, setStudentEmail] = useState("");
  const [studentPassword, setStudentPassword] = useState("");
  const [studentPhoneNo, setStudentPhoneNo] = useState("");
  const [studentDepartment, setStudentDepartment] = useState("");
  const [studentAcademicYear, setStudentAcademicYear] = useState("");
  const handleStudentSubmit = async (e) => {
    e.preventDefault();
    const studentData = {
      name: studentName,
      email: studentEmail,
      password: studentPassword,
      phoneNo: studentPhoneNo,
      department: studentDepartment,
      academicYear: Number(studentAcademicYear),
    };
    const response = await fetch(
      "http://localhost:8080/api/auth/register/student",
      {
        method: "Post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(studentData),
      },
    );
    if (response.ok) {
      alert("Student registered successfully");
      setMode("login");
    } else {
      alert("Registration failed");
    }
  };
  return (
    <div>
      {mode === "login" ? (
        <div>
          <h1>Login</h1>

          <form onSubmit={handleSubmit}>
            <div>
              <label>Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                required
              />
            </div>

            <div>
              <label>Password</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            <button type="submit">Login</button>
            <button onClick={getStudents}>getStudent</button>
            <button onClick={changeMode} type="button">
              Create Account
            </button>
          </form>
        </div>
      ) : (
        <div>
          {role === "student" ? (
            <div>
              <h1>Create Student Account</h1>
              <form onSubmit={handleStudentSubmit}>
                <div>
                  <label>Name</label>
                  <input
                    type="text"
                    value={studentName}
                    onChange={(e) => setStudentName(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Email</label>
                  <input
                    type="email"
                    value={studentEmail}
                    onChange={(e) => setStudentEmail(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>password</label>
                  <input
                    type="password"
                    value={studentPassword}
                    onChange={(e) => setStudentPassword(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Phone No:</label>
                  <input
                    type="text"
                    value={studentPhoneNo}
                    onChange={(e) => setStudentPhoneNo(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Department</label>
                  <input
                    type="text"
                    value={studentDepartment}
                    onChange={(e) => setStudentDepartment(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Academaic Year</label>
                  <input
                    type="number"
                    value={studentAcademicYear}
                    onChange={(e) => setStudentAcademicYear(e.target.value)}
                    required
                  />
                </div>
                <button type="submit">Register</button>
              </form>
              <button onClick={changeRole}>ChangeRole</button>
            </div>
          ) : (
            <div>
              <h1>hello</h1>
              <button onClick={changeRole}>ChangeRole</button>
            </div>
          )}
        </div>
      )}
      {/* <button  onClick={changeMode}>change</button> */}
    </div>
  );
}

export default Auth;
