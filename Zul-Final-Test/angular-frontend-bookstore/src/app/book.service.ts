import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Books } from './books';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseUrl = "http://localhost:8089/api/v1/books";
  
  constructor(private httpClient: HttpClient) { }

  getBooksList():Observable<Books[]>
  {
    return this.httpClient.get<Books[]>(`${this.baseUrl}`);
  }

  createBook(book: Books): Observable<object>
  {
    return this.httpClient.post(`${this.baseUrl}`, book)
  }

  getBookByIsbn(id: number): Observable<Books>
  {
    return this.httpClient.get<Books>(`${this.baseUrl}/${id}`);
  }

  getBookByTitle(title: string): Observable<Books>
  {
    return this.httpClient.get<Books>(`${this.baseUrl}/title/${title}`);
  }

  getBookByAuthor(authors: string): Observable<Books>
  {
    return this.httpClient.get<Books>(`${this.baseUrl}/author/${authors}`);
  }

  // updateBook(id: number, book: Books): Observable<object>
  // {
  //   return this.httpClient.put(`${this.baseUrl}/${id}`, book);
  // }

  updateBook(isbn: number, book: Books): Observable<object>
  {
    return this.httpClient.put(`${this.baseUrl}/${isbn}`, book);
  }

  deleteBook(id: number): Observable<Object>
  {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
