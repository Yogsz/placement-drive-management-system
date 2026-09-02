const apiRequest = async (url, options = {}) => {
    const token = localStorage.getItem("token");

    const response = await fetch(
        `http://localhost:8080${url}`,
        {
            ...options,
            headers: {
                ...options.headers,
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        }
    );

    return response;
};

export default apiRequest;