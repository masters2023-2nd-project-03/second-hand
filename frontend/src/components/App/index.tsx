import { useEffect, useState } from 'react';
import { ThemeProvider } from 'styled-components';
import axios from 'axios';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import StatusBar from '../statusBar';

interface IData {
  id: number;
  name: string;
}

const App = () => {
  const [data, setData] = useState<IData[]>([]);
  const [value, setValue] = useState<string>('');

  const fetchData = async () => {
    try {
      const response = await axios.get('http://3.39.207.31:8080/api/test');
      console.log(response);
      setData(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const addUser = async (name: string) => {
    try {
      await axios.post('http://3.39.207.31:80/api/test', {
        name,
      });

      setValue('');
      fetchData();
    } catch (error) {
      console.error(error);
    }
  };

  const deleteUser = async (id: number) => {
    try {
      await axios.delete(`http://3.39.207.31:80/api/test/${id}`);
      fetchData();
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyles />
        <ul>
          {data.map((user) => (
            <div key={user.id}>
              <li>{user.name}</li>
              <button onClick={() => deleteUser(user.id)}>삭제</button>
            </div>
          ))}
        </ul>
        <input value={value} onChange={(e) => setValue(e.target.value)} />
        <button onClick={() => addUser(value)}>추가</button>
      </ThemeProvider>
      <StatusBar color="black" />
    </>
  );
};

export default App;
