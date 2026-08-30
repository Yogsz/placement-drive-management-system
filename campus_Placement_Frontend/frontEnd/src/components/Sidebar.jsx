import { Link } from "react-router-dom";

function Sidebar({ isSidebarOpen }) {

  return (
    <aside className={`sidebar ${isSidebarOpen ? "open" : "closed"}`}>

      {isSidebarOpen && <h2>Menu</h2>}

      <Link to="/dashboard">
        {isSidebarOpen ? "Home" : "⌂"}
      </Link>

      <Link to="/companies">
        {isSidebarOpen ? "Companies" : "▣"}
      </Link>

      <Link to="/drives">
        {isSidebarOpen ? "Placement Drives" : "▤"}
      </Link>

      <Link to="/interviews">
        {isSidebarOpen ? "Interviews" : "▦"}
      </Link>

      <button>
        {isSidebarOpen ? "Logout" : "↪"}
      </button>

    </aside>
  );
}

export default Sidebar;