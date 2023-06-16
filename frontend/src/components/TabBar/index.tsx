import { useState } from 'react';

import iconfiles from '../../assets/icons/index';
import Icon from '../Icon';
import * as S from './styles';

type IconName = keyof typeof iconfiles;

interface Tab {
  id: number;
  name: IconName;
  label: string;
}

const TABS: Tab[] = [
  { id: 0, name: 'home', label: '홈화면' },
  { id: 1, name: 'newspaper', label: '판매내역' },
  { id: 2, name: 'heart', label: '관심목록' },
  { id: 3, name: 'message', label: '채팅' },
  { id: 4, name: 'person', label: '내 계정' },
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
              <Icon name={tab.name} width={'48'} height={'26'} />
              <span>{tab.label}</span>
            </S.Li>
          ))}
        </S.Ul>
      </S.tabBarMenu>
    </S.tabBarContainer>
  );
};

export default TabBar;
