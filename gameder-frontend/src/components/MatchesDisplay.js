
const MatchesDisplay = ( {matchedGames, setClickedGame} ) => {
    
    return (        
        <div className="matches-display-panel">Game
            {matchedGames?.map((match, index) => (
                <div key={{index}} className="match-card" onClick={() => setClickedGame(match)}>
                    <div class="image-container">
                        <img src={match?.url} alt={match?.name} />
                    </div>
                    <h3>{match?.name}</h3>
                </div>
                ))}
        </div>
    )
}

export default MatchesDisplay; 