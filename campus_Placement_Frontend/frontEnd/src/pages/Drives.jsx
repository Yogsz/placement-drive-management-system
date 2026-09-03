import { useEffect, useState } from "react";
import apiRequest from "../services/api";

function Drives() {
  const [drives, setDrives] = useState([]);
  const [selectedDrive, setSelectedDrive] = useState(null);
  const [applicationCount, setApplicationCount] = useState(0);

  // GET ALL PLACEMENT DRIVES
  useEffect(() => {
    const getDrives = async () => {
      try {
        const response = await apiRequest("/api/drives");

        if (!response.ok) {
          throw new Error(`Request failed: ${response.status}`);
        }

        const data = await response.json();

        setDrives(data);
      } catch (error) {
        console.error("Error fetching drives:", error);
      }
    };

    getDrives();
  }, []);

  // GET APPLICATION COUNT FOR A DRIVE
  const getApplicationCount = async (driveId) => {
    try {
      const response = await apiRequest(
        `/api/applications/drive/${driveId}/count`
      );

      if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
      }

      const data = await response.json();

      setApplicationCount(data);
    } catch (error) {
      console.error("Error fetching application count:", error);
    }
  };

  const applyForDrive = async () => {
    try{
        const studentId = localStorage.getItem("studentId");
        if(!studentId){
            alert("Student ID not Found");
            return;
        }
        const resopose = await apiRequest("/api/applications",{
            method: "POST",
            body: JSON.stringify({
                studentId: Number(studentId),
                driveId: selectedDrive.driveId,
                status:"APPLIED"
            })
        });
        if(!resopose.ok){
            throw new Error(resopose.status);
        }
        alert("Appication Submitted successFully");
        setApplicationCount((count)=>count+1);
    }catch(error){
        console.log(error);
        alert("Failed to apply for this drive");
    }
  }

  return (
    <div className="drives-page">

      <h1>Placement Drives</h1>

      <div className="drives-table-wrapper">
        <table className="drives-table">

          <thead>
            <tr>
              <th>Company</th>
              <th>Job Role</th>
              <th>Salary</th>
              <th>Eligibility</th>
              <th>Apply Before</th>
              <th>Drive Date</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            {drives.map((drive) => (
              <tr
                key={drive.driveId}
                onClick={() => {
                  setSelectedDrive(drive);
                  setApplicationCount(0);
                  getApplicationCount(drive.driveId);
                }}
              >

                <td className="title-column">
                  <strong>{drive.companyName}</strong>
                </td>

                <td>
                  {drive.jobRole}
                </td>

                <td>
                  {drive.salaryPackage} LPA
                </td>

                <td>
                  {drive.eligibility}
                </td>

                <td>
                  {drive.applyBefore}
                </td>

                <td>
                  {drive.driveDate}
                </td>

                <td>
                  <span className="status-badge">
                    <span className="status-dot"></span>
                    {drive.status}
                  </span>
                </td>

              </tr>
            ))}
          </tbody>

        </table>
      </div>

      {/* DRIVE DETAILS */}

      {selectedDrive && (
        <div className="details-overlay">

          <div className="drive-details">

            {/* CLOSE BUTTON */}

            <button
              className="close-button"
              onClick={() => {
                setSelectedDrive(null);
                setApplicationCount(0);
              }}
            >
              ×
            </button>

            {/* COMPANY */}

            <h2>
              {selectedDrive.companyName}
            </h2>

            {/* JOB ROLE */}

            <p className="details-role">
              {selectedDrive.jobRole}
            </p>

            {/* DRIVE DETAILS */}

            <div className="details-grid">

              <div>
                <span>Salary Package</span>
                <strong>
                  {selectedDrive.salaryPackage} LPA
                </strong>
              </div>

              <div>
                <span>Eligibility</span>
                <strong>
                  {selectedDrive.eligibility}
                </strong>
              </div>

              <div>
                <span>Apply Before</span>
                <strong>
                  {selectedDrive.applyBefore}
                </strong>
              </div>

              <div>
                <span>Drive Date</span>
                <strong>
                  {selectedDrive.driveDate}
                </strong>
              </div>

              <div>
                <span>Status</span>
                <strong>
                  {selectedDrive.status}
                </strong>
              </div>

            </div>

            {/* APPLICATION COUNT */}

            <div className="stat-box">

              <span>
                Students Applied
              </span>

              <strong>
                {applicationCount}
              </strong>

            </div>


            <div className="details-footer">

              <button
                className="apply-button"
                onClick={applyForDrive}
              >
                Apply Now
              </button>

            </div>

          </div>

        </div>
      )}

    </div>
  );
}

export default Drives;