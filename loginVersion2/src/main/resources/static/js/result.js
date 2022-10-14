/**
 * @description set win state for a candidate
 * 
 * @param {number} num - number of the candidate, 1 or 2
 * @param {boolean} won - won?
 * @param {number} percentage - voting result for this candidate
 */
 function setWinState(num, won, percentage) {
    const CANDIDATE = document.getElementById(`candidate${num}`);
    if (won) {
        CANDIDATE.classList.remove("lost");
        CANDIDATE.classList.add("won");
        CANDIDATE.querySelector('h3').innerHTML = 'Won';
    }
    else {
        CANDIDATE.classList.add("lost");
        CANDIDATE.classList.remove("won");
        CANDIDATE.querySelector('h3').innerHTML = 'Lost';
    }
    CANDIDATE.querySelector('h4').innerHTML = `${percentage}%`
}

/**
 * @description set elecion's total count of votes
 * 
 * @param {number} count - count of votes in this election
 */
 function setTotalVotes (count) {
    const DESCRIPTION = document.getElementById('count');
    DESCRIPTION.innerHTML = `Total number of votes: ${count}`;
}

// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // how to get query word from the url
    const CURRENT_URL = new URL(window.location.href);
    const QUERY = CURRENT_URL.searchParams.get("q");
    // print query to console
    console.log(QUERY);
    // display title and description
    printTitle('dynamic election title');
    printDescription('dynamic election description');
    // set candidate 2 info
    populateCandidate(2, "Dynamic Name2", 'imgs/candidate.jpg');
    // set total number of votes
    setTotalVotes(4402);
    // example of setting candidate 1 as loser
    setWinState(1, false, 32);
    // example of setting candidate 2 as winner
    setWinState(2, true, 68);
});