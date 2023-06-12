import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './components/App';
import TabBar from './components/TabBar';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);

root.render(
  <React.StrictMode>
    <App />
    <TabBar />
  </React.StrictMode>,
);
