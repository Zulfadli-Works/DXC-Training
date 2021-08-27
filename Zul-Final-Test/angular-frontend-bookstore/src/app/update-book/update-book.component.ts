import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Books } from '../books';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})

export class UpdateBookComponent implements OnInit {

  id!: number;
  book: Books = new Books();
  updatedBook:(boolean) = false;

  constructor(private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.bookService.getBookByIsbn(this.id).subscribe(data => {
      this.book = data;
    }, error => console.log(error));
  }

  onSubmit()
  {
    this.bookService.updateBook(this.id, this.book).subscribe( data =>{
      console.log(data);
      this.goToBookList();
    }
    ,error => console.log(error))
  }

  goToBookList()
  {
    this.updatedBook = true;
    // this.router.navigate(['/books']);
    setTimeout(() => {
      this.router.navigate(['books']);
  }, 5000);  //5s
  }

}
