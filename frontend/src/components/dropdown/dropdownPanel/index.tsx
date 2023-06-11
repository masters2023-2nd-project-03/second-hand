import * as S from './styles';

interface DropdownPanelProps {
  option: string;
  onClick: (option: string) => void;
}

const DropdownPanel: React.FC<DropdownPanelProps> = ({ option, onClick }) => {
  return (
    <S.dropdownPanel onClick={() => onClick(option)}>
      <S.optionTitle>{option}</S.optionTitle>
    </S.dropdownPanel>
  );
};

export default DropdownPanel;
