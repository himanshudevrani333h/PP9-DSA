// function DashInsertII(num) { 
//     let arr = [];
//     let temp = num;
//     // console.log(temp)
//     arr.push(String(parseInt(temp %10)));
//     temp = parseInt(temp/10)
//     let idx = 0;
//     while(temp != 0){
//       if( arr[idx] %2 == 0){
//          let rem = temp % 10;
//          if(rem % 2 == 0){
//           arr[++idx] = "*";
//           arr[++idx] = (String(rem));
//          }else{
//           arr[++idx] =(String(rem));
//          }
//       }else{
//          let rem = temp % 10;
//          if(rem % 2 == 0){
//            arr[++idx] =(String(rem));
//          }else{
//           arr[++idx] ="-";
//            arr[++idx] =(String(rem));
//          }
//       }
//       //  temp /= 10;
//        temp = parseInt(temp/10)
//     }
//     let ans = "";
//     for( let i =arr.length-1; i>=0; i--) ans += arr[i];
//     // console.log(ans);
//     return ans; 
//   }
     
//   // keep this function call here 
//   console.log(DashInsertII(99946));

// cool
// let a = []
// let b = []

// console.log(a===b)