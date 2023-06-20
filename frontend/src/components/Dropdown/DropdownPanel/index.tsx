import * as S from './styles';

interface DropdownPanelProps {
  option: string;
  onClick: (option: string) => void;
  isLastPanel?: boolean;
}

const DropdownPanel = ({
  option,
  onClick,
  isLastPanel,
}: DropdownPanelProps) => {
  return (
    <S.DropdownPanel onClick={() => onClick(option)} isLastPanel={isLastPanel}>
      <S.OptionTitle isLastPanel={isLastPanel}>{option}</S.OptionTitle>
    </S.DropdownPanel>
  );
};

export default DropdownPanel;
