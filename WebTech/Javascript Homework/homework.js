function fibonacci(num) {
    if (num <= 1) return 1;
  
    return fibonacci(num - 1) + fibonacci(num - 2);
  }

  for (var i = 1; i<=10; i++)
  {
      document.write(fibonacci(i), "<br>");
  }
  document.write("<br>");

  function multiplicationTable(num)
  {
      for(var i = 0; i<13; i++)
      {
        document.write(num, " x ", i, " = ", num*i, "<br>"); 
      }
  }

  multiplicationTable(8);
  document.write("<br>");


var arr1 = [3, 8, 7, 6, 5, -4, -3, 2, 1];
// nums.sort();
// document.write(nums, "<br>");

arr1.sort(compare);

function compare(a, b)
{
    return a-b;
}
document.write(arr1, "<br>");
document.write(arr1, "<br>");

var a = [
    [1, 2, 1, 24],
    [8, 11, 9, 4],
    [7, 0, 7, 27],
    [7, 4, 28, 14],
    [3, 10, 26, 7]
];

for(var i = 0; i< a.length; i++)
{
    document.write("<br>", "<br>");
    document.write("Row: ", i);
    for(var p = 0; p < a.length-1; p++)
    {
        document.write("<br>");
        document.write(a[i][p]);
    }
    
}