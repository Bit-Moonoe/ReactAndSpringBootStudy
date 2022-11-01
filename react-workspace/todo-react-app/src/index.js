import React from 'react';
import ReactDOM, { createRoot } from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Todo from './Todo';
import AppRouter from './AppRouter';

const container = document.getElementById('root');

const root = createRoot(container);

root.render(<AppRouter tab = "home"/>);

reportWebVitals();
// console.log(reportWebVitals());
ReactDOM.render(
  <React.StrictMode>
    <AppRouter/>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
