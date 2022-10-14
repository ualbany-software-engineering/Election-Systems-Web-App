/**
 * @description set user name in home page dynamically
 * 
 * @param {string} username - username of the logged in user
 */
function welcomeUser (username) {
    const WELCOME_HEADER = document.querySelector('h1');
    WELCOME_HEADER.innerHTML = `Welcome, ${username}!`;
}

/**
 * @description append an election to a list
 * 
 * @param {string} title - username of the logged in user
 * @param {string} q - query (unique) name for election
 * @param {Element} list - html element of a list
 * @param {boolean} done - is election finished?
 */
 function addElection (title, q, list, done=false) {
    if (!done) {
        list.innerHTML += `<a href="voting.html?q=${q}"><li>${title}</li></a>`
    } else {
        list.innerHTML += `<a href="result.html?q=${q}"><li>${title}</li></a>`
    }
}

// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // display username
    welcomeUser('dynamic username');
    // get list of elections
    const CURRENT_ELECTIONS = document.querySelector('#current ul');
    const RECENT_ELECTIONS = document.querySelector('#recent ul');
    // example of adding elections with js
    addElection('dynamic current election', q='dynamic_current', CURRENT_ELECTIONS, false);
    addElection('dynamic recent election', q='dynamic_recent', RECENT_ELECTIONS, true);

});