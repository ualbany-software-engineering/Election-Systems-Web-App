// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // how to get query word from the url
    const CURRENT_URL = new URL(window.location.href);
    const QUERY = CURRENT_URL.searchParams.get("q");
    // print query to console
    console.log(QUERY);
    // display title and description
    printTitle('Ford vs Chevy);
    printDescription('Which car manufacturer do you prefer?');
    // set candidate 1 info
    populateCandidate(1, "Dynamic Name1", 'imgs/candidate.jpg');
});