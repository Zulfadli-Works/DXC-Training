import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Books } from '../books';
import { Router } from '@angular/router'

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  book: Books = new Books();
  bookAdded:(boolean) = false;

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void 
  {

  }

  saveBook()
  {
    this.bookService.createBook(this.book).subscribe(data =>{
      console.log(data);
      this.goToBookList();
    },
    error => console.log(error));
  }

  goToBookList()
  {
    // this.router.navigate(['/books']);
    this.bookAdded = true;
    
    setTimeout(() => {
      this.router.navigate(['books']);
  }, 5000);  //5s
  }

  onSubmit()
  {
    console.log(this.book);
    this.saveBook();
  }

}
