// array to store old results' objects
let results = [
    {
        q: "pluto",
        title: "Pluto: Planet or Not",
        description: "Is pluto a planet?",
        total: 434,
        candidate1: {
            name: "Yes",
            img: "imgs/Pluto.jpg",
            percent: 43
        },
        candidate2:  {
            name: "No",
            img: "imgs/sad pluto.jpeg",
            percent: 57
        }
    },
    {
        q: "flat_earth",
        title: "Earth: Flat or Not",
        description: "Is Earth flat or round?",
        total: 1420,
        candidate1: {
            name: "Round",
            img: "imgs/earth.jpg",
            percent: 97
        },
        candidate2:  {
            name: "Flat",
            img: "imgs/flat.jpg",
            percent: 3
        }
    }
]


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

/**
 * @description gets vote info from result and display it
 * 
 * @param {string} query - query word of this result
 */
 function displayResult (query) {
    for (result of results) {
        if (result.q === query) {
            // display title and description
            printTitle(result.title);
            printDescription(result.description);
            // set candidate 1 info
            populateCandidate(1, result.candidate1.name, result.candidate1.img);
            // set candidate 2 info
            populateCandidate(2, result.candidate2.name, result.candidate2.img);
            // set total number of votes
            setTotalVotes(result.total);
            // example of setting candidate 1 as loser
            setWinState(1, result.candidate1.percent > result.candidate2.percent, result.candidate1.percent);
            // example of setting candidate 2 as winner
            setWinState(2, result.candidate2.percent > result.candidate1.percent, result.candidate2.percent);
        } 
    }
}

// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // how to get query word from the url
    const CURRENT_URL = new URL(window.location.href);
    const QUERY = CURRENT_URL.searchParams.get("q");
    // print result
    displayResult(QUERY);
});