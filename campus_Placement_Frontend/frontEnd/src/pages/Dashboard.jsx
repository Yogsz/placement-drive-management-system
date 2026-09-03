import apiRequest from "../services/api";
import { useEffect, useState } from "react";

function Dashboard() {
  const [student, setStudent] = useState(null);
  useEffect(() => {
    const getStudent = async () => {
      const response = await apiRequest("/api/students/me");
      const data = await response.json(); 

      setStudent(data);
      localStorage.setItem("studentId", data.id)
      console.log(localStorage.getItem("studentId"));
    };

    getStudent();
  }, []);

  const hour = new Date().getHours();
  let greeting;
  if (hour >= 5 && hour < 12) {
    greeting = "Good Morning";
  } else if (hour >= 12 && hour < 17) {
    greeting = "Good Afternoon";
  } else if (hour >= 17 && hour < 21) {
    greeting = "Good Evening";
  } else {
    greeting = "Good Night";
  }
  return (
    <div className="dashboard-content">
      <h1>
        {greeting}, {student?.name}
      </h1>

      <p>
        All the best for your placement journey! Stay focused, keep learning,
        and give your best.👍
      </p>
    </div>
  );
}

export default Dashboard;
