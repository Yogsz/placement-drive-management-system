import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import apiRequest from "../services/api";
// import "./Companies.css";

function Companies() {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    const getCompanies = async () => {
      try {
        const response = await apiRequest("/api/companies");

        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }

        const data = await response.json();
        setCompanies(data);
      } catch (error) {
        console.log(error);
      }
    };

    getCompanies();
  }, []);

  return (
    <div className="companies-page">

      <div className="companies-header">
        <div>
          <h1>Company Directory</h1>
          <p>{companies.length} Companies Listed</p>
        </div>

        <input
          type="text"
          placeholder="Search here..."
          className="company-search"
        />
      </div>

      <div className="companies-container">
        {companies.map((company, index) => (
          <Link
            to={`/companies/${company.companyId}`}
            className="company-card"
            key={company.companyId}
          >
            <div className="company-number">
              {index + 1}
            </div>

            <h2>{company.companyName}</h2>
          </Link>
        ))}
      </div>

    </div>
  );
}

export default Companies;