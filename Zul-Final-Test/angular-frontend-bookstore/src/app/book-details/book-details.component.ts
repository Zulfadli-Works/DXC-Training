import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Books } from '../books';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id!: number;
  book!: Books;
  books!: Books[];
  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.book = new Books();
    this.bookService.getBookByIsbn(this.id).subscribe( data => {
      this.book = data;
    });
  }

  private getBooks()
  {
    this.bookService.getBooksList().subscribe(data => {
      this.books = data;
    })
  }

  updateBook(id: number)
  {
    this.router.navigate(['update-book', id]);
  }

  deleteBook(id: number)
  {
    this.bookService.deleteBook(id).subscribe( data => {
      console.log(data);
      this.getBooks();
    })
    this.router.navigate(['/books']);
  }

}
