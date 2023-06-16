import { Link } from 'react-router-dom';
import { useState } from 'react';

import iconfiles from '../../assets/icons/index';
import Icon from '../Icon';
import * as S from './styles';

type IconName = keyof typeof iconfiles;

interface Tab {
  id: number;
  name: IconName;
  label: string;
  path: string;
}

const TABS: Tab[] = [
  { id: 0, name: 'home', label: '홈화면', path: '/' },
  { id: 1, name: 'newspaper', label: '판매내역', path: '/sales' },
  { id: 2, name: 'heart', label: '관심목록', path: '/heart' },
  { id: 3, name: 'message', label: '채팅', path: '/chat' },
  { id: 4, name: 'person', label: '내 계정', path: '/account' },
];

const TabBar = () => {
  const [activeTab, seActiveTab] = useState(0);

  const handleClick = (id: number) => {
    seActiveTab(id);
  };

  return (
    <S.tabBarContainer>
      <S.tabBarMenu>
        <S.Ul>
          {TABS.map((tab: Tab) => (
            <S.Li
              key={tab.id}
              active={activeTab === tab.id}
              onClick={() => handleClick(tab.id)}
            >
              <Link to={tab.path}>
                <Icon name={tab.name} width={'48'} height={'26'} />
              </Link>
              <span>{tab.label}</span>
            </S.Li>
          ))}
        </S.Ul>
      </S.tabBarMenu>
    </S.tabBarContainer>
  );
};

export default TabBar;
