function Navbar({ toggleSidebar, isSidebarOpen }) {
  return (
    <nav className="navbar">

      <button
        className="sidebar-toggle"
        onClick={toggleSidebar}
      >
        {isSidebarOpen ? "←" : "→"}
      </button>

      <h2>Campus Placement Tracker</h2>

    </nav>
  );
}

export default Navbar;