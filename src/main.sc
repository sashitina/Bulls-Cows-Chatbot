require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: common.js
    module = sys.zb-common
    
require: getNumber.js
    
theme: /

    state: Правила
        q!: $regex</start>
        intent!: /Давай поиграем
        a: Сыграем в "Быки и коровы". Загадаю 4-значное число с неповторяющимися цифрами, ты попытаешься отгадать. Каждая угаданная цифра - это "корова". Цифра, угаданная вплоть до позиции - "бык". Начнем? 
        go!: /Правила/Согласен?
        
        state: Согласен?
            
            state: Да
                intent: /Согласие
                go!: /Игра

            state: Нет
                intent: /Несогласие
                a: Жаль! Если передумаешь - скажи "давай поиграем".
                
    state: Игра
        # сгенерируем случайное число и перейдем в стейт /Проверка
        script:
            $session.number = getRandomNumber();
            $reactions.answer("Загадано {{$session.number}}");
            $reactions.transition("/Проверка");
            
    state: Проверка
        intent: /Число
        script:
            # сохраняем введенное пользователем число
            var user_number = $parseTree._Number;
            $session.win = {bulls:4, cows:0};
            $session.check = getHint($session.number, user_number)

            # проверяем угадал ли пользователь загаданное число и выводим соответствующую реакцию
            if ($session.check == $session.win) {
                $reactions.answer("Ты выиграл!");
                $reactions.transition("/Правила/Согласен?");
            }
            else
                $reactions.answer("Твой результат: {{ $session.check }}");
                

    state: NoMatch || noContext = true
        event!: noMatch
        random:
            a: Я не понял.
            a: Что ты имеешь ввиду?
            a: Ничего не пойму