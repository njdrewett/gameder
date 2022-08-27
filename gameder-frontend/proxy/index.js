
const axios = require("axios").default;

const PORT = 8000

const express = require('express')

const app = express() 

const signupURL = "localhost:8080"

const gamersAllURL = "http://localhost:8080/api/gamer/all"

const gamesURL = "http://localhost:8080/api/game/all"

const emailExists = "http://localhost:8080/api/gamer/emailExists"

app.get('/', (request, response) => {
    response.json('running ')
})

app.post('/signup', async (request, response) => {
    console.log('signup ')
    
    const {email, password} = request.body;

    const options = {
        method: 'POST',
        url: gamersAllURL
    }

    await axios.request(options).then(( callResponse) => {
        console.log(callResponse.data);

    }).then(res => res.json())
    .then((data) => {
        console.log(data);
    })
    .catch( (error) => {
        console.error(error);
    })


})

app.get('/gamers', async (request, response) => {
    console.log('gamers')
    // make configurable via env
    const options = {
        method: 'GET',
        url: gamersAllURL
    }

    await axios.request(options).then(( callResponse) => {
        console.log(callResponse.data);
        response.json(callResponse.data);
    }).catch( (error) => {
        console.error(error);
    })


})

app.get('/games', async (request, response) => {
    console.log('games')
    // make configurable via env
    const options = {
        method: 'GET',
        url: gamesURL
    }

    await axios.request(options).then(( callResponse) => {
        console.log(callResponse.data);
        response.json(callResponse.data);
    }).catch( (error) => {
        console.error(error);
    })


})

app.listen(PORT, () => console.log('Server running on PORT ' + PORT))

