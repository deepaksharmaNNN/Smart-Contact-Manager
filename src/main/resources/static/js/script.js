console.log('Hello from script.js');

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});



// todo
function changeTheme() {
    // set to web page
    document.querySelector('html').classList.add(currentTheme);
    //set the listener to change theme button
    const changeThemeButton =  document.querySelector('#theme_change_button');
    changeThemeButton.querySelector('span').textContent = currentTheme === 'light' ? 'Dark' : 'Light';
    changeThemeButton.addEventListener('click', () => {
        // change theme
        // console.log('change theme');
        currentTheme = currentTheme === 'light' ? 'dark' : 'light';
        // set to web page
        document.querySelector('html').classList.remove(currentTheme === 'light' ? 'dark' : 'light');
        document.querySelector('html').classList.add(currentTheme);
        // set to local storage
        setTheme(currentTheme);
        //change text of button
        changeThemeButton.querySelector('span').textContent = currentTheme === 'light' ? 'Dark' : 'Light';
    });
}
// set theme to local storage
function setTheme(theme) {
    localStorage.setItem('theme', theme);
}

// get theme from local storage
function getTheme() {
    let theme = localStorage.getItem('theme');
    return theme = theme ? theme : 'light';
}