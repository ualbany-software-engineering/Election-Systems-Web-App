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
    // get list of elections
    const ALL_ELECTIONS = document.querySelector('#old ul');
    // example of adding elections with js
    addElection('dynamic old election', q='dynamic_old', ALL_ELECTIONS, false);
});