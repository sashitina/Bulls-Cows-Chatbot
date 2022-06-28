require: slotfilling/slotFilling.sc
  module = sys.zb-common
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
            var bot_number = getRandomNumber();
            $session.number = number
            $reactions.transition("/Проверка");
            
    state: Проверка
        intent: /Число
        script:
            # сохраняем введенное пользователем число
            var user_number = $parseTree._Number;

            # проверяем угадал ли пользователь загаданное число и выводим соответствующую реакцию
            if (num == $session.number) {
                $reactions.answer("Ты выиграл! Хочешь еще раз?");
                $reactions.transition("/Правила/Согласен?");
            }
            else
                if (num < $session.number)
                    $reactions.answer(selectRandomArg(["Мое число больше!", "Бери выше", "Попробуй число больше"]));
                else $reactions.answer(selectRandomArg(["Мое число меньше!", "Подсказка: число меньше", "Дам тебе еще одну попытку! Мое число меньше."]));

    state: NoMatch || noContext = true
        event!: noMatch
        random:
            a: Я не понял.
            a: Что ты имеешь ввиду?
            a: Ничего не пойму