import { useEffect, useState } from "react";

function Interviews() {

    const [interviews, setInterviews] = useState([]);

    useEffect(() => {

        const getInterviews = async () => {

            try {

                const response = await fetch(
                    "http://localhost:8080/api/interviews/my-interviews",
                    {
                        method: "GET",
                        credentials: "include"
                    }
                );

                if (!response.ok) {
                    throw new Error("Failed to fetch interviews");
                }

                const data = await response.json();

                setInterviews(data);

            } catch (error) {

                console.error("Error fetching interviews:", error);

            }
        };

        getInterviews();

    }, []);

    return (
        <div>
            <h1>Interviews</h1>

            {interviews.length === 0 ? (
                <p>No interviews found.</p>
            ) : (
                interviews.map((interview) => (
                    <div key={interview.interviewId}>

                        <p>
                            Date: {interview.interviewDate}
                        </p>

                        <p>
                            Time: {interview.interviewTime}
                        </p>

                        <p>
                            Mode: {interview.mode}
                        </p>

                        <p>
                            Status: {interview.status}
                        </p>

                    </div>
                ))
            )}
        </div>
    );
}

export default Interviews;
