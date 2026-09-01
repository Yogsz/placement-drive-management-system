import { useState } from "react";
import {useNavigate} from "react-router-dom";

function Login() {
    const [email, setEmail] = useState("");
    const [password,setpassword] = useState("");
    const navigate = useNavigate();
    const handleSubmit = (e) => {
        e.preventDefault();

        if (email === "") {
            alert("Please enter your email");
            return;
        }

        if (password === "") {
            alert("Please enter your password");
            return;
        }

        console.log("Email:", email);
        console.log("Password:", password);
        navigate("/dashboard");
    };

    return (
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
                    onChange={(e)=> setpassword(e.target.value)} 
                    required/>
                </div>


                <button type="submit">
                    Login
                </button>
            </form>
        </div>
    );
}

export default Login;