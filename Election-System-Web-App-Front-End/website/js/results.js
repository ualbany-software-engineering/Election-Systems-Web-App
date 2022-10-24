// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // get list of elections
    const ALL_ELECTIONS = document.querySelector('#old ul');
    // example of adding elections with js
    addElection('dynamic old election', q='dynamic_old', ALL_ELECTIONS, true);
});