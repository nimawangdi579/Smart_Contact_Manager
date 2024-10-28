
let currentTheme = getTheme();
// Initially change theme when page loads
document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
})

function changeTheme() {
  // Set the page theme based on the current theme
  changePageTheme(currentTheme, "");

  // Set the listener for the theme change button
  const changeThemeButton = document.querySelector("#theme_change_button");

  // Check if button exists
  if (changeThemeButton) {
    changeThemeButton.addEventListener("click", () => {
      const oldTheme = currentTheme; // Store the old theme before changing

      // Toggle the theme
      currentTheme = currentTheme === "dark" ? "light" : "dark";

      console.log(currentTheme);
      changePageTheme(currentTheme, oldTheme); // Apply the new theme
    });
  } else {
    console.error("Theme change button not found!");
  }
}

// Set theme to localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

// Change the current page theme
function changePageTheme(theme, oldTheme) {
  // Update the theme in localStorage
  setTheme(theme);

  // Remove the old theme
  if (oldTheme) {
    console.log("Removing old theme:", oldTheme);
    document.querySelector("html").classList.remove(oldTheme);
  }

  // Add the new theme
  document.querySelector("html").classList.add(theme);

  // Update the button text
  document.querySelector("#theme_change_button span").textContent = 
    theme === "light" ? "Dark" : "Light";
}
