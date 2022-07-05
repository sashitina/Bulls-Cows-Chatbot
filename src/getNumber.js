function getRandomNumber(){
  return shuffle( "0123456789".split('') ).join('').substring(0,4);
}

function shuffle(o){
    for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
    return o;
}

function getHint(secret, guess){
  var map = {};
  var A = 0;
  var B = 0;
  var bulls = []
  var cows = []
  
  var secret2 = secret.toString();
  var guess2 = guess.toString();
  
  for (var i = 0; i < 10; i++) map[i] = 0;
  for (i = 0; i < secret2.length; i++) {
    if (secret2[i] === guess2[i]) A++;
    else {
      map[secret2[i]]++;
      B += map[secret2[i]] <= 0 ? 1 : 0;
      map[guess2[i]]--;
      B += map[guess2[i]] >= 0 ? 1 : 0;
    }
  }
  
  if (A == 0) bulls = 'быков';
  else if (A == 1) bulls = 'бык';
  else bulls = 'быка'
  
  if (B == 0) cows = 'коров';
  else if (B == 1) cows = 'корова';
  else cows = 'коровы'
  
  return A + bulls + ', ' + B + cows;
}