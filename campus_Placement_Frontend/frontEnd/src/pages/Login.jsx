import { use, useState } from "react";

function Login(){
    const [email, setEmail] = useState("");
    return (<div>
        <h1>Login</h1>
        <form>
            <div>
                <label>Email</label>
                <input type="email" value={email} onChange={(e)=>{setEmail(e.target.value)}}/>
            </div>
            <div>
                <label>Password</label>
                <input type="password" />
            </div>
            <p>Your email : {email}</p>
            <button type="submit" onClick={()=>{setEmail("poda potta")}}>Fill email</button>
            <button type="submit">Login</button>
        </form>
    </div>
    );
}

export default Login;