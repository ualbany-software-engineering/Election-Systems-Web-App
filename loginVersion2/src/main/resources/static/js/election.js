/**
 * @description set elecion title dynamically
 * 
 * @param {string} title - title of the current election
 *
 function printTitle (title) {
    const TITLE = document.querySelector('h1');
    TITLE.innerHTML = `${title}`;
}

/**
 * @description set elecion description dynamically
 * 
 * @param {string} description - description of the current election
 *
 function printDescription (description) {
    const DESCRIPTION = document.getElementById('description');
    DESCRIPTION.innerHTML = `${description}`;
}

/**
 * @description set candidate info
 * 
 * @param {number} num - number of the candidate, 1 or 2
 * @param {string} name - Candidate's name
 * @param {string} imgPath - image path of the candidate
 *
 function populateCandidate (num, name, imgPath) {
    const CANDIDATE = document.getElementById(`candidate${num}`);
    CANDIDATE.querySelector('.candidate_name').innerHTML = `${name}`;
    CANDIDATE.querySelector('img').setAttribute('src', imgPath);
}*/