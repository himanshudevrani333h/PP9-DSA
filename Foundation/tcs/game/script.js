let number_generator = Math.floor(Math.random() * 101) + 1;
console.log(number_generator);
let div = document.querySelector(".container");
let btn = document.querySelector(".start");
let count = 0;
btn.addEventListener("click", function (e) {
    let inp = document.createElement("input");
    inp.classList.add("inp");
    inp.setAttribute("placeholder", "Enter Number Only")

    let label = document.createElement("label");
    label.innerText = "Enter Your Number";

    let entered_choice = document.createElement("div")
    entered_choice.classList.add("choice");
    entered_choice.innerText = "your choice";

    let submit = document.createElement("button");
    submit.classList.add("start")
    submit.innerText = "Submit";

    div.append(label);
    div.append(inp);
    div.append(submit)
    div.append(entered_choice);


    submit.addEventListener("click", function (e) {
        if (document.querySelector(".ans")) {
            let ans = document.querySelector(".ans")
            ans.remove();
        }
        let entered_number = inp.value;
        if (!Number.isInteger(Number(entered_number)) || Number(entered_number) <= 0 ||  Number(entered_number) > 100 || entered_number == "") {
            if (entered_number == "") {
                alert("Please Enter a Number!")
                return;
            } else {
                alert("Please Enter a Valid number!")
                inp.value = "";
                return
            }
        }
        count++;
        if (count < 10) {
            entered_choice.innerText += " " + entered_number;
            inp.value = "";
            let ans = document.createElement("div")
            ans.classList.add("ans");
            let str = "";
            if (entered_number > number_generator) {
                str = "Your guess is High";
                ans.innerText = str;
                console.log(ans.innerText);
                div.append(ans);
            } else if (entered_number < number_generator) {
                str = "Your guess is Low";
                ans.innerText = str;
                console.log(ans.innerText);
                div.append(ans);
            } else {
                let inp2 = document.querySelector(".inp");
                console.log(inp2)
                let label2 = document.querySelector("label");
                let entered_choice2 = document.querySelector(".choice");
                let sub = document.querySelector(".start");
                inp2.remove();
                label2.remove();
                entered_choice2.remove();
                sub.remove();
                str = "you won!"
                ans.style.backgroundColor = "green";


                ans.innerText = str;
                console.log(ans.innerText);


                let btn2 = document.createElement("button");
                btn2.classList.add("start")
                btn2.innerText = "PlayAgain!"
                btn2.addEventListener("click", function (e) {
                    location.reload();
                })
                div.append(btn2);
                div.append(ans);
            }
        } else {
            let ans = document.createElement("div")
            let btn2 = document.createElement("button");
            btn2.classList.add("start")
            ans.classList.add("ans");
            if (entered_number == number_generator) {
                ans.innerText = "You Won!";
                ans.style.backgroundColor = "green";
                btn2.innerText = "Retry!";
            } else {
                ans.innerText = "All Attempt Failed!";
            }
            let inp2 = document.querySelector(".inp");
            let label2 = document.querySelector("label");
            console.log(label2)
            let entered_choice2 = document.querySelector(".choice");
            let sub = document.querySelector(".start");
            inp2.remove();
            label2.remove();
            entered_choice2.remove();
            sub.remove();


            if (btn2.innerText == "") {
                btn2.innerText = "Retry!"
            }

            btn2.addEventListener("click", function (e) {
                location.reload();
            })
            div.append(btn2);
            div.append(ans);
        }

    })

    btn.remove();
})
